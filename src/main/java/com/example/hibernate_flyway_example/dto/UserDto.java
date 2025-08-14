package com.example.hibernate_flyway_example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<OrderDto> orders;
}
