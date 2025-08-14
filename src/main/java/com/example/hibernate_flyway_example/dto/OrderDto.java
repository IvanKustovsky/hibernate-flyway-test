package com.example.hibernate_flyway_example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
}
