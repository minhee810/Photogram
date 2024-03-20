package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeDto {

    private int id;
    private String username;
    private String profileImageUrl;

    private Integer subscribeState;
    private Integer equalUserState; // 로그인한 사용자와 동일인이면 구독, 구독 취소 버튼 안보여주기 위해 필요

    // Integer 로 받은 이유 : maria db는 true 값을 int 로 설정하면 값을 받아오지 못함.

}
