package com.example.refrigerator.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    FRUIT("과일",1),
    MEET("육류",2)
    ;

    private final String CategoryName;
    private final int CategoryNum;

//    ROOT("카테고리", null),
//        FRUIT("과일",ROOT),
//            APPLE("사과",FRUIT),
//        VEG("야채",ROOT);

}
