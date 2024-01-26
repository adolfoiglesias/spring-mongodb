package com.amichdev.spring.mongodb.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("categories")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Category {

    @Id
    UUID id;
    private String name;
}
