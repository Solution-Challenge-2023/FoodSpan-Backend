package com.example.refrigerator.controller;


import com.example.refrigerator.domain.Item;
import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.dto.ItemRequestDto;
import com.example.refrigerator.dto.ItemResponseDto;
import com.example.refrigerator.dto.RefrigeratorRequestDto;
import com.example.refrigerator.dto.RefrigeratorResponseDto;
import com.example.refrigerator.service.ItemService;
import com.example.refrigerator.service.RefrigeratorService;
import com.example.refrigerator.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/refrigerators")
@ResponseStatus
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    // 냉장고 이름, 메모 정보 받아 냉장고 등록
    @PostMapping()
    public ResponseEntity<RefrigeratorResponseDto> save(@RequestBody RefrigeratorRequestDto requestDto) {
        Refrigerator refrigerator = refrigeratorService.save(requestDto);
        return ResponseDto.ok(new RefrigeratorResponseDto(refrigerator));
    }

    // 냉장고 id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<RefrigeratorResponseDto> findById(@PathVariable Long id) {
        Refrigerator refrigerator = refrigeratorService.findById(id);
        return ResponseDto.ok(new RefrigeratorResponseDto(refrigerator));
    }

    // 냉장고 모두 조회
    @GetMapping()
    public ResponseEntity<List<RefrigeratorResponseDto>> findAll() {
        List<RefrigeratorResponseDto> refrigerators = refrigeratorService.findAll();
        return ResponseDto.ok(refrigerators);
    }

    // 냉장고 이름, 메모 수정
    @PutMapping("/{id}")
    public ResponseEntity<RefrigeratorResponseDto> update(@PathVariable Long id, @RequestBody RefrigeratorRequestDto requestDto) {
        Refrigerator refrigerator = refrigeratorService.update(id, requestDto);
        return ResponseDto.ok(new RefrigeratorResponseDto(refrigerator));
    }

    // 냉장고 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        refrigeratorService.delete(id);
    }
}
