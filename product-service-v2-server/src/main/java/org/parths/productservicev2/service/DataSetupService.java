package org.parths.productservicev2.service;

import org.parths.productservicev2.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DataSetupService implements ApplicationRunner {

    @Autowired
    private IProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (String arh:
                args.getSourceArgs()) {
            System.out.println("arguement...." + arh);
        }
        ProductDto p1 = new ProductDto("Apple", "Apple Gala", 140d);
        ProductDto p2 = new ProductDto("Mango", "Mango Devgadh", 600d);
        ProductDto p3 = new ProductDto("Grapes", "Grapes from Masic", 300d);

        List<ProductDto> productDtoList = List.of(p1,p2,p3);

        productService.getAllProducts()
                .doOnNext(
                        productDto1 -> productDtoList.remove(productDto1)
                ).transformDeferred( productDtoFlux -> Flux.fromIterable(productDtoList))
                .flatMap( productDto2 ->
                        productService.createProduct(Mono.just(productDto2)))
                .subscribe();

    }
}
