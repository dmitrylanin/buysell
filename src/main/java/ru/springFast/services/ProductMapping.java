package ru.springFast.services;

import org.springframework.stereotype.Service;
import ru.springFast.dto.ProductDTO;
import ru.springFast.models.Producer;
import ru.springFast.models.Product;

@Service
public class ProductMapping {

    public ProductDTO mapToProductDto(Product entity){
        ProductDTO productDTO = new ProductDTO();
        if(entity.getId() != null){
            productDTO.setId(entity.getId());
        }
        productDTO.setDescription(entity.getDescription());
        productDTO.setPrice(entity.getPrice());
        productDTO.setCity(entity.getCity());
        productDTO.setTitle(entity.getTitle());
        productDTO.setAuthor(entity.getAuthor());
        productDTO.setProducer(entity.getProducer().getName());
        return productDTO;
    }

    public Product mapToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCity(productDTO.getCity());
        product.setTitle(productDTO.getTitle());
        product.setAuthor(productDTO.getAuthor());
        product.setProducer(new Producer(productDTO.getProducer()));
        return product;
    }
}
