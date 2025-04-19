package com.example.mfschain.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MFSChain集群接口文档")
                        .description("海事区块链链子链核心操作")
                        .version("v1")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("WeiHuang")
                                .url("https://www.shmtu.edu.cn")
                                .email("weihuang@shmtu.edu.cn")
                        ))
                .externalDocs(new ExternalDocumentation()
                        .description("项目API文档")
                        .url("/"));
    }
}
