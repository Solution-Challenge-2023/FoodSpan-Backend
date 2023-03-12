package com.example.refrigerator.controller;

import com.example.refrigerator.domain.Item;
import com.example.refrigerator.dto.ItemRequestDto;
import com.example.refrigerator.dto.ItemResponseDto;
import com.example.refrigerator.service.ItemService;
import com.example.refrigerator.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/refrigerators")
public class ItemController {
    private final ItemService itemService;

    // 냉장고 id, 카테고리별로 아이템 조회
    // sortby = "added" / "expiration"
    // response : <itemId, ItemName, category, count, expiration date>
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemResponseDto>> findAll(@PathVariable Long id, @RequestParam(required = false) Long categoryId, @RequestParam(defaultValue = "added") String sortBy) {
        List<ItemResponseDto> items = itemService.findAll(id, categoryId, sortBy);
        return ResponseDto.ok(items);
    }

    // 냉장고 리스트에서 상품 선택했을때 나오는 디테일 정보
    // response : <itemId, ItemName, category, Add date, count, expiration date>
    @GetMapping("{id}/items/{itemId}")
    public ResponseEntity<ItemResponseDto> findOne(@PathVariable Long id, @PathVariable Long itemId) {
        ItemResponseDto item = itemService.findOne(id, itemId);
        return ResponseDto.ok(item);
    }
    // 단건 수정
    @PutMapping("{id}/items/{itemId}")
    public ResponseEntity<ItemRequestDto> update(@PathVariable Long id, @PathVariable Long itemId, @RequestBody ItemRequestDto item) {
        ItemResponseDto newItem =  itemService.update(id, itemId, item);
        return ResponseDto.ok(item);
    }

    @PostMapping("/items")
    public ResponseEntity<List<Item>> save(@RequestBody List<Item> items) {
        List<Item> saveditems = itemService.save(items);
        return ResponseDto.ok(saveditems);
    }

    // 다건 수정
    @PutMapping("/items")
    public ResponseEntity<List<ItemResponseDto>> update(@RequestBody List<Item> items) {
        itemService.updateAll(items);
        List<ItemResponseDto> updated = itemService.findAll(3L, 3L, "added");
        return ResponseDto.ok(updated);
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
}
