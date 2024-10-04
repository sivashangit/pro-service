package com.siva.springproduct.controller;

import com.siva.springproduct.dto.ProductRequestDto;
import com.siva.springproduct.dto.ProductResponseDto;
import com.siva.springproduct.entity.Product;
import com.siva.springproduct.service.Productservcie;
import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private Productservcie productservcie;

    @PostMapping("/product")
    ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto productRequestDto){

        Product product= productservcie.createProduct(productRequestDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
        ResponseEntity<List<ProductResponseDto>> getallproducts(){

        List<ProductResponseDto> ans=productservcie.getallproducts();
        return new ResponseEntity<>(ans,HttpStatus.OK);

        }

        @GetMapping("/{id}")
        ResponseEntity<Product> getproductByid(@PathVariable int id){

        Product product=productservcie.getproductByid(id);

        return new ResponseEntity<>(product,HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        ResponseEntity<String> deleteproduct(@PathVariable int id){
            int num=productservcie.deleteproduct(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }

        @GetMapping("/{id}/stock")
        ResponseEntity<Optional<Integer>> getstcokQuatityByid(@PathVariable int id){
        Optional<Integer> num=productservcie.getStockById(id);
        return new ResponseEntity<>(num,HttpStatus.OK);
        }

    @GetMapping("/test")
    public String welcome(){

        return "welocme";
    }
}
