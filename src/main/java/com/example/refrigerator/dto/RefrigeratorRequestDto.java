package com.example.refrigerator.dto;

import com.example.refrigerator.domain.Refrigerator;
import com.sun.istack.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefrigeratorRequestDto {
    @NotNull
    private String name;

    private String memo;

//    @Builder
//    public RefrigeratorRequestDto (String name, String memo){
//        this.name = name;
//        this.memo = memo;
//    }

    public Refrigerator toEntity(){
        return Refrigerator.builder()
                .name(name)
                .memo(memo)
                .build();
    }
}
