package org.parths.productservicev2.client;

import org.parths.productservicev2.dto.ProductDto;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ProductServiceClient {

    private WebClient webClient;

    public ProductServiceClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductDto> getProduct(String id){
        return webClient
                .get()
                .uri("product/{id}", id)
                .retrieve()
                .bodyToMono(ProductDto.class);

    }
}
