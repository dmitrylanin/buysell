package ru.buyselltests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.springFast.Main;
import ru.springFast.models.Product;
import ru.springFast.services.ProductService;

@SpringBootTest(classes = Main.class)
public class MainTest {
    @Autowired
    public ProductService productService;

    private long productId;
    private String productTitle;
    private String productDescription;
    private int productPrice;
    private String productCity;
    private String productAuthor;

    @Test
    @Transactional
    public void test() {
        this.createProduct();
    }

    private void createProduct() {
        final Product testProduct1 = new Product();
        testProduct1.setTitle("iPhone 15");
        testProduct1.setDescription("Смартфон");
        testProduct1.setPrice(65);
        testProduct1.setCity("Moscow");
        testProduct1.setAuthor("Elina");

        this.productService.saveProduct(testProduct1);
        this.productId = testProduct1.getId();

        final Product testProduct2 = productService.getProductById(productId);
        assertEquals((long) testProduct2.getId(), this.productId, "Product identifier is valid!");
    }
}