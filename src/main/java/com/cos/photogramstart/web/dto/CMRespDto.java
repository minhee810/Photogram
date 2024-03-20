package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T> {   // generic 사용 (응답 Dto)
    private int code;  // 1(성공), -1(실패)
    private String message;
    private T data;
}
