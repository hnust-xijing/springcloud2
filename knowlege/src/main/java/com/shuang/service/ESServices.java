package com.shuang.service;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class ESServices {

    @Autowired
    private RestHighLevelClient restHighLevelClient;




    //获取这些数据实现搜索功能
    public List<Map<String,Object>> searchPage(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo<=1||pageNo>1000){
            pageNo=1;
        }
        if(pageSize>1000){
            pageSize=1000;
        }
        //条件搜索
        SearchRequest searchRequest=new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder= QueryBuilders.termQuery("title",keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("title");
        //关闭一个字段的多个高亮
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        ArrayList<Map<String,Object>> list=new ArrayList<>();
        //解析结果
        for (SearchHit hit : searchResponse.getHits().getHits()){

            Map<String, HighlightField> highlightFields =hit.getHighlightFields();
            HighlightField title=highlightFields.get("title");
            Map<String,Object> sourceAsMap=hit.getSourceAsMap();//原来的结果
        //解析高亮的字段，将原来的替换为我们的
            if(title!=null){
                Text[] fragments=title.fragments();
                String n_title="";
                for (Text text:fragments){
                    n_title+=text;
                }
                sourceAsMap.put("title",n_title);//高亮字段替换为原来的内容即可
            }


            list.add(sourceAsMap);
        }
        return list;

    }


}
