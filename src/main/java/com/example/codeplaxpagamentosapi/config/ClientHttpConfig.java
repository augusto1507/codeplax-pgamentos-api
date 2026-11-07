package com.example.codeplaxpagamentosapi.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientHttpConfig {

    @Bean
    public RestClient pagamentosClient(
            @Value("${PAGAMENTOS_URL :http//:172.20.10.3:8080}") String url){
        return RestClient.builder()
                .baseUrl(url)
                .build();
    }

}
