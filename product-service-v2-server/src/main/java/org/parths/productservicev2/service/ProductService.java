package org.parths.productservicev2.service;

import org.parths.productservicev2.dto.ProductDto;
import org.parths.productservicev2.entity.Product;
import org.parths.productservicev2.mapper.IProductMapper;
import org.parths.productservicev2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductMapper productMapper;

    @Override
    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(product -> productMapper.productToProductDto(product));
    }

    @Override
    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id)
                .map(product -> productMapper.productToProductDto(product));
    }

    @Override
    public Mono<ProductDto> createProduct(Mono<ProductDto> productDto) {
        return productDto.map( productDto1 -> productMapper.productDtoToProduct(productDto1))
                .flatMap(mappedProduct -> productRepository.insert(mappedProduct))
                .map(savedProduct -> productMapper.productToProductDto(savedProduct));
    }

    @Override
    public Mono<ProductDto> updateProduct(String id, ProductDto productDto) {
        return productRepository.findById(id)
                .flatMap(productFromDb -> {
                            Product requestedProduct = productMapper.productDtoToProduct(productDto);
                            requestedProduct.setId(id);
                            return productRepository.save(requestedProduct);
                        }
                ).map(savedProduct -> productMapper.productToProductDto(savedProduct));
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Flux<ProductDto> search(Double priceRangeMin, Double priceRangeMax) {
        Range<Double> range = Range.closed(priceRangeMin, priceRangeMax);
        return productRepository.findByPriceBetween(range)
                .map(product -> productMapper.productToProductDto(product));
    }
}
