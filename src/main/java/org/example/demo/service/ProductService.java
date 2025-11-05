package org.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.example.demo.entity.ProductModel;
import org.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.util.HtmlUtils;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll().stream().peek(it -> it.setData(HtmlUtils.htmlEscape(it.getData()))).toList();
    }
}
