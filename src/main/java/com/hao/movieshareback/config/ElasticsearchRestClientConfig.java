package com.hao.movieshareback.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchRestClientConfig {
    @Value("${es.host}")
    private String ipAddress;

    @Value("${es.port}")
    private Integer port;

    @Value("${es.scheme}")
    private String scheme;

    @Bean(name = "highLevelElasticsearchClient")
    public RestHighLevelClient highLevelClient(){
        HttpHost httpHost = new HttpHost(ipAddress,port,scheme);
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }
}
