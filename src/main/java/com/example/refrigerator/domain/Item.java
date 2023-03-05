package com.example.refrigerator.domain;

import com.example.refrigerator.dto.ItemRequestDto;
import com.example.refrigerator.util.Category;
import com.example.refrigerator.util.DeletedType;
import com.example.refrigerator.util.ExpiredType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor
@Builder
@Entity
@SQLDelete(sql = "UPDATE item SET isDeleted = true, deletedAt = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "isDeleted = false")
public class Item {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략 따라간다
    private Long id;

    @Column(nullable = false, length = 255)
    private String itemName;

    @Column(nullable = false)
    private Long count;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "refrigeratorId")
    private Refrigerator refrigerator;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private Long category;

    @Column
    private LocalDateTime expiredAt;

    @Column
    private ExpiredType expiredType;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    private boolean isDeleted = Boolean.FALSE;
    private Timestamp deletedAt;
    private DeletedType deletedType = DeletedType.EXPIRED;

    public void update(ItemRequestDto item) {
        this.itemName = item.getItemName();
        this.expiredAt = item.getExpiredAt();
        this.category = item.getCategoryId();
        this.count = item.getCount();
        this.expiredType = item.getExpiredType();
    }

    public void updateCount(Item item) {
        this.itemName = item.getItemName();
        this.count = item.getCount();
    }
}
