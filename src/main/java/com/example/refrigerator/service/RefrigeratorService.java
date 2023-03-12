package com.example.refrigerator.service;

import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.dto.RefrigeratorRequestDto;
import com.example.refrigerator.repository.RefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefrigeratorService {

    public final RefrigeratorRepository refrigeratorRepository;

    @Transactional // sql 작업 선언
    public Refrigerator save(RefrigeratorRequestDto requestDto) {
        return refrigeratorRepository.save(requestDto.toEntity());
    }

    public Refrigerator findById(Long id) {
        Refrigerator refrigerator = refrigeratorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 냉장고가 없습니다. id=" + id));

        return refrigerator;
    }

    public List<Refrigerator> findAll() {
        return refrigeratorRepository.findAll();
    }

    @Transactional
    public Refrigerator update(Long id, RefrigeratorRequestDto requestDto) {
        Refrigerator refrigerator = refrigeratorRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 냉장고가 없습니다. id="+id));

        //JPA 의 영속성 컨텍스트 덕분에 entity 객체의 값만 변경하면 자동으로 변경사항 반영함!
        //따라서 repository.update 를 쓰지 않아도 됨.
        refrigerator.update(requestDto.getName(), requestDto.getMemo());
        return refrigerator;
    }

    @Transactional
    public Long delete(Long id) {
        Refrigerator refrigerator = refrigeratorRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 냉장고가 없습니다. id="+id));
        refrigeratorRepository.delete(refrigerator);
        return id;
    }
}
