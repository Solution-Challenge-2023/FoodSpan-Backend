package com.example.refrigerator.dto;

import com.example.refrigerator.domain.Item;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

@Getter
public class ItemResponseDto {

    private Long id;
    private String itemName;
    private Long count;
    private Long categoryId;
    private Long refridgeratorId;

    private Integer dday;
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.count = item.getCount();
        this.categoryId = item.getCategory();
        this.dday = expiredAt == null ? null : getDDAY(item.getExpiredAt());
        this.refridgeratorId = item.getRefrigerator().getId();
        this.expiredAt = item.getExpiredAt();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }

    // dday 계산
    public Integer getDDAY(LocalDateTime expiredAt) {
        LocalDate startDate = LocalDate.now();
        Period period = Period.between(startDate, expiredAt.toLocalDate());
        return period.getDays();
    }
}

