//package in.pateash.MinIOSearchEngine.service;
//
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.SearchHit;
//
//@Service
//public class ElasticsearchService {
//
//    @Autowired
//    private RestHighLevelClient client;
//
//    public void searchIndex(String indexName, String searchContent) throws Exception {
//        SearchRequest searchRequest = new SearchRequest(indexName);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        searchSourceBuilder.query(QueryBuilders.matchQuery("content", searchContent));
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse searchResponse = client.search(searchRequest);
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            // Output each document's content
//            System.out.println(hit.getSourceAsString());
//        }
//    }
//}
