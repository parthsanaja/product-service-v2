package org.parths.productservicev2.service;

import org.parths.productservicev2.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDto> getAllProducts();

    Mono<ProductDto> getProduct(String id);

    Mono<ProductDto> createProduct(Mono<ProductDto> productDto);

    Mono<ProductDto> updateProduct(String id, ProductDto productDto);

    Mono<Void> deleteProduct(String id);

    Flux<ProductDto> search(Double priceRangeMin, Double priceRangeMax);
}
