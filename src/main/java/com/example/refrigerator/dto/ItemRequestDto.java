package com.example.refrigerator.dto;

import com.example.refrigerator.util.ExpiredType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemRequestDto {
    private String itemName;
    private Long count;
    private Long categoryId;
    private LocalDateTime expiredAt;
    private ExpiredType expiredType;

}
