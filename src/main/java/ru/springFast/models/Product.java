package ru.springFast.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hibernate_sequence_for_products")
    @SequenceGenerator(name = "hibernate_sequence_for_products", sequenceName = "hibernate_sequence_for_products", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "city")
    private String city;

    @Column(name = "author")
    private String author;
}
