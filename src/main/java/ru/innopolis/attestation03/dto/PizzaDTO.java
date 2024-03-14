package ru.innopolis.attestation03.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
