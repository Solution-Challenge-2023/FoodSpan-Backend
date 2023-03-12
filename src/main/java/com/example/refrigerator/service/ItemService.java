package com.example.refrigerator.service;

import com.example.refrigerator.domain.Item;
import com.example.refrigerator.domain.Refrigerator;
import com.example.refrigerator.dto.ItemRequestDto;
import com.example.refrigerator.dto.ItemResponseDto;
import com.example.refrigerator.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    public final ItemRepository itemRepository;

    public List<ItemResponseDto> findAll(Long id, Long categoryId, String sortBy) {

        List<Item> items;
         //카테고리별 조회 (필터링 적용)
        if (categoryId != null) {
            if (sortBy.equals("added")) { // 최근추가순 정렬
                items = itemRepository.findItemByCategoryRecently(id, categoryId);
            } else {
                items = itemRepository.findItemByCategoryExpired(id, categoryId);
            }
        }
        // 전체 조회
        else {
            // TODO: 유저 검사
            if (sortBy.equals("added")) { // 최근추가순 정렬
                items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            } else {
                items = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "expiredAt"));
                // TODO: 유통기한 D-DAY 변환
            }
        }

        return items.stream().map(item -> new ItemResponseDto(item)).collect(Collectors.toList());
    }

    @Transactional
    public ItemResponseDto findOne(Long id, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"냉장고에 해당 상품이 없습니다. id=" + itemId));
        return new ItemResponseDto(item);
    }

    // 단건 수정
    @Transactional
    public ItemResponseDto update(Long id, Long itemId, ItemRequestDto item) {
        Item exitem = itemRepository.findById(itemId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 상품이 없습니다. itemId="+itemId));
        exitem.update(item);
        return new ItemResponseDto(exitem);
    }
    // 다건 수정
    @Transactional
    public void updateAll(List<Item> items) {
        for(Item item : items) {
            Item exItem = itemRepository.findById(item.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND ,"해당 상품이 없습니다. itemId="));
            exItem.updateCount(item);
        }
    }


    // itemName, count, fridge, 카테고리, 유통기한or소비기한, 날짜
    @Transactional
    public List<Item> save(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    @Transactional
    public void deleteOne(Long itemId) {
        itemRepository.deleteById(itemId);
    }
    //

    @Transactional
    public void deleteAll(List<Long> ids) {
        itemRepository.deleteAllById(ids);
    }
}
