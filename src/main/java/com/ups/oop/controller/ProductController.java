package com.ups.oop.controller;

import com.ups.oop.dto.*;
import com.ups.oop.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private  final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO){
        return this.productService.createProduct(productDTO);
    }

    @GetMapping("/get-all-product")
    public ResponseEntity getAllProduct(){
        return this.productService.getAllProduct();
    }

    @GetMapping("/get-product")
    public ResponseEntity getProductById(@RequestParam String id){
        return this.productService.getProductbyId(id);
    }

    @PutMapping("/update-product")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO){
        return this.productService.updateProduct(productDTO);
    }

    @DeleteMapping("/remove-product")
    public ResponseEntity deleteProduct(@RequestParam String id){
        return this.productService.deleteProductById(id);
    }
}
