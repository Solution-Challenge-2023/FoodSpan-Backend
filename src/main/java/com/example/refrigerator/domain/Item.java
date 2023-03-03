package com.example.refrigerator.domain;

import com.example.refrigerator.util.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략 따라간다
    private int id;

    @Column(nullable = false, length = 255)
    private String itemName;

    @Column(nullable = false)
    private int count;

    @ManyToOne
    @JoinColumn(name = "refrigeratorId")
    private Refrigerator refrigerator;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Timestamp expiredAt;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;
}
