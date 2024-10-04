package com.siva.springproduct.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String name;
    private String description;
    private String productType;
    private int quantity;
    private double price;
    private String supplierName;
    private String supplierCode;
}
