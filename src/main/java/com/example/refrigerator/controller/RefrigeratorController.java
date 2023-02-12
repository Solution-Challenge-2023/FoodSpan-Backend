package com.example.refrigerator.controller;


import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.dto.RefrigeratorRequestDto;
import com.example.refrigerator.service.RefrigeratorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/refrigerator")
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    // 냉장고 이름, 메모 정보 받아 냉장고 등록
    @PostMapping()
    public int save(@RequestBody RefrigeratorRequestDto requestDto) {
        return refrigeratorService.save(requestDto).getId();
    }

    // 냉장고 id로 조회
    @GetMapping("/{id}")
    public Refrigerator findById(@PathVariable int id) {
        return refrigeratorService.findById(id);
    }

    // 냉장고 모두 조회
    @GetMapping()
    public List<Refrigerator> findAll() {
        return refrigeratorService.findAll();
    }

    // 냉장고 이름, 메모 수정
    @PutMapping("/{id}")
    public Refrigerator update(@PathVariable int id, @RequestBody RefrigeratorRequestDto requestDto) {
        return refrigeratorService.update(id, requestDto);
    }

    // 냉장고 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        refrigeratorService.delete(id);
    }
}
