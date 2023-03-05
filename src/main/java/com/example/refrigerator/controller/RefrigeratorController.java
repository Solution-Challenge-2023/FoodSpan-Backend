package com.example.refrigerator.controller;


import com.example.refrigerator.domain.Item;
import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.dto.ItemRequestDto;
import com.example.refrigerator.dto.ItemResponseDto;
import com.example.refrigerator.dto.RefrigeratorRequestDto;
import com.example.refrigerator.service.ItemService;
import com.example.refrigerator.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/refrigerators")
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;
    private final ItemService itemService;

    // 냉장고 이름, 메모 정보 받아 냉장고 등록
    @PostMapping()
    public Long save(@RequestBody RefrigeratorRequestDto requestDto) {
        return refrigeratorService.save(requestDto).getId();
    }

    // 냉장고 id로 조회
    // 냉장
    @GetMapping("/{id}")
    public Refrigerator findById(@PathVariable Long id) {
        return refrigeratorService.findById(id);
    }

    // 냉장고 모두 조회
    @GetMapping()
    public List<Refrigerator> findAll() {
        return refrigeratorService.findAll();
    }

    // 냉장고 이름, 메모 수정
    @PutMapping("/{id}")
    public Refrigerator update(@PathVariable Long id, @RequestBody RefrigeratorRequestDto requestDto) {
        return refrigeratorService.update(id, requestDto);
    }

    // 냉장고 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        refrigeratorService.delete(id);
    }



    // 냉장고 id, 카테고리별로 아이템 조회
    // sortby = "added" / "expiration"
    // response : <itemId, ItemName, category, count, expiration date>
    @GetMapping("/{id}/items")
    public List<ItemResponseDto> findAll(@PathVariable Long id, @RequestParam(required = false) Long categoryId, @RequestParam(defaultValue = "added") String sortBy) {
        System.out.println("sortBy = " + sortBy);
        return itemService.findAll(id, categoryId, sortBy);
    }

    // 냉장고 리스트에서 상품 선택했을때 나오는 디테일 정보
    // response : <itemId, ItemName, category, Add date, count, expiration date>
    @GetMapping("{id}/items/{itemId}")
    public ItemResponseDto findOne(@PathVariable Long id, @PathVariable Long itemId) {
        return itemService.findOne(id, itemId);
    }
    // 단건 수정
    @PutMapping("{id}/items/{itemId}")
    public Item update(@PathVariable Long id, @PathVariable Long itemId, @RequestBody ItemRequestDto item) {
        return itemService.update(id, itemId, item);
    }

    @PostMapping("/items")
    public String save(@RequestBody List<Item> items) {
        return itemService.save(items).toString();
    }

    // 다건 수정
    @PutMapping("/items")
    public List<ItemResponseDto> update(@RequestBody List<Item> items) {
        itemService.updateAll(items);
        return itemService.findAll(3L, 3L, "added");
    }

    // 단건 삭제
    @DeleteMapping("{id}/items/{itemId}")
    public void deleteOne(@PathVariable Long itemId) {
        itemService.deleteOne(itemId);
    }

    // 전체 삭제
    @DeleteMapping("/items")
    public void deleteAll(@RequestBody List<Long> ids) {
        itemService.deleteAll(ids);
    }
    // 상품 추가는 s
    // 단건 상품 직접 추가
    // 냉장고

    // 상품 스캔한 단건 상품 추가

    // 영수증에서 뽑힌 리스트 다건 추가

}
