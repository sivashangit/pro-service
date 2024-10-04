package com.siva.springproduct.service;

import com.siva.springproduct.config.RestConfig;
import com.siva.springproduct.dto.ProductRequestDto;
import com.siva.springproduct.dto.ProductResponseDto;
import com.siva.springproduct.dto.Valuemapper;
import com.siva.springproduct.entity.Product;
import com.siva.springproduct.exception.ProductNotFound;
import com.siva.springproduct.exception.ProductServiceBusinessException;
import com.siva.springproduct.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Productservcie {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestConfig restConfig;
    public Product createProduct(ProductRequestDto productRequestDto){

        try {
            Product product=Valuemapper.dtotoproduct(productRequestDto);
            return productRepository.save(product);

        }catch (Exception e){
            //logger.error("Error occurred while creating product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create product", e); // Throw custom exception
        }

    }
    @Cacheable(value="products")
    public List<ProductResponseDto> getallproducts(){
        List<ProductResponseDto> list=null;
        try {

            log.info("ProductService:getProducts execution started.");
            List<Product> products = productRepository.findAll();
            if (!products.isEmpty()) {

                list = products.stream().map(Valuemapper::producttodto).collect(Collectors.toList());
            } else {
                list = Collections.emptyList();
            }
        }catch (Exception e){
            log.error("Exception occurred while retrieving products from database , Exception message {}", e.getMessage());
            throw new ProductServiceBusinessException("Exception occurred while fetch all products from Database");
        }
        log.info("ProductService:getProducts execution ended.");
        return list;

    }
    public Product getproductByid(int id){


            try {
             return productRepository.findById(id)
                        .orElseThrow(()->new ProductNotFound("ProductNot found"+id));

            }
            catch (Exception e){

                throw new ProductNotFound("error occered while get this product"+id,e);

            }
    }

    public int deleteproduct(int id){

        productRepository.deleteById(id);
        return id;
    }

    public Optional<Integer> getStockById(int id){

        String url="http://localhost:8181/stock/"+id;
        ResponseEntity<Optional<Integer>> response= restConfig.restTemplate().getForEntity(url, (Class<Optional<Integer>>) (Object) Optional.class);
        System.out.println("service called and executed...........");
        return response.getBody();
    }
}
