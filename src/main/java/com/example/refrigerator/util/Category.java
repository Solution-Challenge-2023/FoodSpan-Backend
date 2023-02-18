package com.example.refrigerator.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    VEGETABLE("채소",1),
    FRUIT("과일",2),
    CEREALS("곡류", 3),
    NUT("견과류",4),
    MEAT("육류",5),
    SEAFOOD("수산물",6),
    NOODLE("면류",7),
    SAUCE("소스",8),
    MILK("유제품",9),
    BEVERAGE("음료",10),
    DISH("요리/반찬",11),
    INSTANT("인스턴트",12),
    DESERT("디저트",13),
    ETC("기타",14)
    ;

    private final String CategoryName;
    private final int CategoryNum;

//    ROOT("카테고리", null),
//        FRUIT("과일",ROOT),
//            APPLE("사과",FRUIT),
//        VEG("야채",ROOT);

}
