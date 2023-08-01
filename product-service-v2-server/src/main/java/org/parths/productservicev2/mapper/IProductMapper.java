package org.parths.productservicev2.mapper;

import org.parths.productservicev2.dto.ProductDto;
import org.parths.productservicev2.entity.Product;

public interface IProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

}
