package com.example.refrigerator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블 자동으로 생성됨
public class Refrigerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String memo;

    @ManyToOne(fetch = FetchType.EAGER) // 들고올때 정보 다 필요하니까
    @JoinColumn(name = "userId")
    private User user;

    // 단순 조인해서 값을 가져오기 위함, 칼럼 필요없음
    @OneToMany(mappedBy = "refrigerator", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다 -> DB에 칼럼을 만들지 마세요
    private List<Item> item;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    // TODO: Soft delete로 변경
    private Timestamp deletedAt;

    public void update(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }
}
