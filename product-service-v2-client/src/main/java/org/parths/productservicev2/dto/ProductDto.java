package org.parths.productservicev2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProductDto {

    private String id;

    private String name;

    private String description;

    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto that = (ProductDto) o;

        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    public ProductDto(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
