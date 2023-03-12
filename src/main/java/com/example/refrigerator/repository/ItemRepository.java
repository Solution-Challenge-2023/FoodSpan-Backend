package com.example.refrigerator.repository;

import com.example.refrigerator.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // select * from refrigerator r and item i where i.refrigeratorId = ? and i.category = ?
    @Query(value = "select i from Item i where i.refrigerator.id = ?1 and i.category = ?2 order by createdAt desc")
    List<Item> findItemByCategoryRecently(Long refrigeratorId, Long categoryId);

    @Query(value = "select i from Item i where i.refrigerator.id = ?1 and i.category = ?2 order by expiredAt ASC")
    List<Item> findItemByCategoryExpired(Long refrigeratorId, Long categoryId);


    List<Item> findByRefrigeratorIdAndCategoryOrderByCreatedAtDesc(Integer refrigeratorId, Integer categoryId);
}
