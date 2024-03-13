package com.cos.photogramstart.handler.ex;

public class CustomException extends RuntimeException {  // exception 이 되기 위해서 상속 받기

    // 객체를 구분할 때 사용 -> 중요한 건 아니고, jvm 에게 중요한 것.
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }


}
