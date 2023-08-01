package org.parths.productservicev2.mapper;

import org.parths.productservicev2.dto.ProductDto;
import org.parths.productservicev2.entity.Product;
import org.springframework.stereotype.Component;

/*
Use MapStruct library
 */
@Component
public class ProductMapper implements IProductMapper{
    @Override
    public ProductDto productToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
