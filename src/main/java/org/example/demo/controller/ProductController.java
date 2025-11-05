package org.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.example.demo.entity.ProductModel;
import org.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/data")
    public List<ProductModel> getData() {
        return productService.getAllProducts();
    }
}
