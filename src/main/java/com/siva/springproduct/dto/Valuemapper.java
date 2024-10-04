package com.siva.springproduct.dto;

import com.siva.springproduct.entity.Product;

public class Valuemapper {


    public static Product dtotoproduct(ProductRequestDto productRequestDto){

        Product product=new Product();

        product.setDescription(productRequestDto.getDescription());
        product.setProductType(productRequestDto.getProductType());
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(product.getQuantity());
        product.setSupplierCode(productRequestDto.getSupplierCode());
        product.setSupplierName(productRequestDto.getSupplierName());

        return product;



    }
    public static ProductResponseDto producttodto(Product product){

        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setProductType(product.getProductType());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setQuantity(product.getQuantity());
        productResponseDto.setDesc(product.getDescription());
        productResponseDto.setSupplierCode(product.getSupplierCode());
        productResponseDto.setSupplierName(product.getSupplierName());
        return productResponseDto;

    }
}
