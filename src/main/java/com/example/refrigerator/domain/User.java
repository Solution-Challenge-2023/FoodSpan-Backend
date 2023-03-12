package com.example.refrigerator.domain;

import com.example.refrigerator.util.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블 자동으로 생성됨
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략 따라간다
    private Long id;

    @Column(nullable = false,length = 20)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    @ColumnDefault("'user'")
    // @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private UserType role;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
