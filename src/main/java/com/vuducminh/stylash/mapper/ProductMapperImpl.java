package com.vuducminh.stylash.mapper;

import com.vuducminh.stylash.controller.dto.CreateProductRequest;
import com.vuducminh.stylash.controller.dto.ProductDto;
import com.vuducminh.stylash.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl implements ProductMapper{

    @Override
    public Product toProduct(CreateProductRequest createProductRequest) {
//        if (createProductRequest == null) {
//            return null;
//        }
//        return new Product(createProductRequest.getName(), createProductRequest.getImage_url());
        return null;
    }

    @Override
    public ProductDto toProductDto(Product product) {
//        if (product == null) {
//            return null;
//        }
//        ProductDto.CategoryDto categoryDto = new ProductDto.CategoryDto(product.getCategory().getName());
//        return new ProductDto(product.getId(), product.getName(), product.getImage_url());
        return null;
    }
}
