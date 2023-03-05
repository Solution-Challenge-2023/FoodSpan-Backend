package com.example.refrigerator.service;

import com.example.refrigerator.domain.Item;
import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.repository.ItemRepository;
import com.example.refrigerator.repository.RefrigeratorRepository;
import com.example.refrigerator.util.ExpiredType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemServiceTest {
    @Autowired
    private ItemRepository itemRepository;
    @Test
    void update() {
        // given
        Item item1 = new Item();
        item1.setItemName("아이템1");
        item1.setCategory(3L);
        item1.setCount(2L);
        item1.setExpiredAt(LocalDateTime.now());
        item1.setExpiredType(ExpiredType.EXPIRY_DATE);


        // 테이블에 id값이 있으면 update, 없으면 insert 실행
        itemRepository.save(item1);

//
//        // when
//        List<Refrigerator> refrigeratorList = refrigeratorRepository.findAll();
//
//        // then
//        Refrigerator refrigerator = refrigeratorList.get(0);
//        Assertions.assertThat(refrigerator.getName()).isEqualTo(name);
//        Assertions.assertThat(refrigerator.getMemo()).isEqualTo(memo);
    }

    @Test
    void save() {
    }
}