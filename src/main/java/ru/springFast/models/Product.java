package ru.springFast.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence_for_products")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producer_id", nullable = true)
    private Producer producer;
}