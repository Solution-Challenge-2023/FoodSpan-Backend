package com.example.refrigerator.domain.refrigerator;

import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.repository.RefrigeratorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RefrigeratorRepositoryTest {

    @Autowired
    private RefrigeratorRepository refrigeratorRepository;

    @AfterEach
    // 단위 테스트 끝날때마다 삭제
    public void cleanUp(){
        refrigeratorRepository.deleteAll();
    }

    @Test
    public void 냉장고_저장_불러오기(){
        // given
        String name = "테스트 냉장고 이름";
        String memo = "테스트 냉장고 메모";

        // 테이블에 id값이 있으면 update, 없으면 insert 실행
        refrigeratorRepository.save(Refrigerator.builder()
                .name(name)
                .memo(memo)
                .build());
        // when
        List<Refrigerator> refrigeratorList = refrigeratorRepository.findAll();

        // then
        Refrigerator refrigerator = refrigeratorList.get(0);
        Assertions.assertThat(refrigerator.getName()).isEqualTo(name);
        Assertions.assertThat(refrigerator.getMemo()).isEqualTo(memo);
    }
}
