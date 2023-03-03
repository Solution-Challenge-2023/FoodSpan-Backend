package com.example.refrigerator.repository;

import com.example.refrigerator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 자동으로 bean 등록됨
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {
}
