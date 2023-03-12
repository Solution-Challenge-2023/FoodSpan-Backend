package com.example.refrigerator.repository;

import com.example.refrigerator.domain.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
}
