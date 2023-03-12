package com.example.refrigerator.repository;

import com.example.refrigerator.domain.Item;
import com.example.refrigerator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 자동으로 bean 등록됨
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.username = ?1")
    User findUserByName(String username);

    @Query(value = "select u from User u where u.email = ?1")
    User findUserByEmail(String email);
}
