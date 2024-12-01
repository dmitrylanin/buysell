package ru.springFast.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String title;

    private String description;

    private float price;

    private String city;

    private String author;

    private String producer;
}
