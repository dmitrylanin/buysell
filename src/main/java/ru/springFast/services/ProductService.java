package ru.springFast.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.springFast.aspect.ToDBLog;
import ru.springFast.dto.ProductDTO;
import ru.springFast.models.Producer;
import ru.springFast.models.Product;
import ru.springFast.repositories.ProducerRepository;
import ru.springFast.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProducerRepository producerRepository;
    @Autowired
    private final ProductMapping productMapping;

    /*
        Моя собственная аннотация для аспектов
     */
    @ToDBLog
    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(ProductDTO productDTO) {
        Product product = productMapping.mapToProduct(productDTO);

        if(producerRepository.existsByName(product.getProducer().getName())){
            log.info("У товара бренд есть: " + product.getProducer().getName());
            product.setProducer(producerRepository.findByName(product.getProducer().getName()));
            productRepository.save(product);
        }else{
            if(product.getProducer() == null){
                product.setProducer(null);
                productRepository.save(product);
            }else{
                Producer producer = product.getProducer();
                producerRepository.save(producer);
                product.setProducer(producer);
                productRepository.save(product);
            }
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}