package com.ups.oop.service;

import com.ups.oop.dto.ProductDTO;
import com.ups.oop.entity.Product;
import com.ups.oop.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private List<ProductDTO> productDTOList = new ArrayList<>();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity createProduct(ProductDTO productDTO) {
        String productId = productDTO.getId();
        //check repository if record exist
        Optional<Product> productOptional = productRepository.findByProductId(productId);
        if(productOptional.isPresent()) {
            String errorMessage = "Product with id " + productId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(productDTO.getProduct_description().contains(" ")) {
                //Build Person and save in Repository
                Product productRecord = new Product();
                productRecord.setId(productRecord.getId());
                String[] nameStrings = productDTO.getProduct_description().split(" ");
                String name = nameStrings[0];
                productRecord.setName(name);
                productRepository.save(productRecord);
                return ResponseEntity.status(HttpStatus.OK).body(productDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllProduct() {
        List<ProductDTO> productList = getProduct();
        if(productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    public List<ProductDTO> getProduct() {
        Iterable<Product> productIterable = productRepository.findAll();
        List<ProductDTO> productList = new ArrayList<>();
        for(Product pro : productIterable) {
            ProductDTO product = new ProductDTO();
            product.setDistributor(Arrays.asList(pro.getDistributor().getName()));
            product.setProduct_description(pro.getName());
            productList.add(product);
        }
        return productList;
    }

    public ResponseEntity getProductbyId(String productId) {
        Optional<Product> productOptional = productRepository.findByProductId(productId);
        if(productOptional.isPresent()) {
            //if record was found
            Product productFound = productOptional.get();
            ProductDTO product = new ProductDTO(productFound.getId().toString(), productFound.getProductId(),
                    productFound.getName(),productFound.getPrice(), Arrays.asList(productFound.getDistributor().getName()));
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            //if record wasn't found
            String errorMessage = "Product with id " + productId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateProduct(ProductDTO productDTO) {
        String requestId = productDTO.getId();
        //check repository if record exist
        Optional<Product> productOptional = productRepository.findByProductId(requestId);
        if(productOptional.isPresent()) {
            //If record exists, then perform Update
            Product product = productOptional.get();
            if(productDTO.getProduct_description().contains(" ")) {
                //Build Person and save in Repository
                product.setId(Long.valueOf(requestId));
                String[] nameStrings = productDTO.getProduct_description().split(" ");
                String name = nameStrings[0];
                product.setName(name);
                productRepository.save(product);
                return ResponseEntity.status(HttpStatus.OK).body(productDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Employee with id " + requestId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteProductById(String id) {
        String message = "Product with id " + id;
        Optional<Product> productOptional = productRepository.findByProductId(id);
        if(productOptional.isPresent()) {
            //If record was found, then delete record
            productRepository.delete(productOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}