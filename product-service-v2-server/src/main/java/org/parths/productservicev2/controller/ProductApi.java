package org.parths.productservicev2.controller;

import org.parths.productservicev2.dto.ProductDto;
import org.parths.productservicev2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductApi {

    @Autowired
    private IProductService productService;

    @GetMapping("all")
    public Flux<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProduct(@PathVariable String id) {

        return productService.getProduct(id)
                .map(productDto -> ResponseEntity.ok(productDto))
                .defaultIfEmpty(ResponseEntity.badRequest().build());

    }

    @PostMapping("")
    public Mono<ProductDto> createProduct(@RequestBody Mono<ProductDto> productBody) {
        return productService.createProduct(productBody);
    }

    @PutMapping("{id}")
    public Mono<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto updatedProductBody) {
        return productService.updateProduct(id, updatedProductBody);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id)
                .map(ResponseEntity::ok)
                ;
    }

    @GetMapping("search")
    public Flux<ProductDto> search(@RequestParam Double priceRangeMin, @RequestParam Double priceRangeMax){
        return productService.search(priceRangeMin, priceRangeMax);

    }

}
