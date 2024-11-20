package ru.springFast.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import ru.springFast.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


}