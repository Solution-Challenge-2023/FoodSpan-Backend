package com.example.refrigerator.dto;

import com.example.refrigerator.domain.Item;
import com.example.refrigerator.domain.Refrigerator;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Getter
public class RefrigeratorResponseDto {

    private Long id;
    private String name;
    private String memo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RefrigeratorResponseDto(Refrigerator refrigerator) {
        this.id = refrigerator.getId();
        this.name = refrigerator.getName();
        this.memo = refrigerator.getMemo();
        this.createdAt = refrigerator.getCreatedAt();
        this.updatedAt = refrigerator.getUpdatedAt();
    }
}

