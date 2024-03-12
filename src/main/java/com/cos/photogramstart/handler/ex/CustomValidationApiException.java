package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {  // exception 이 되기 위해서 상속 받기

    // 객체를 구분할 때 사용 -> 중요한 건 아니고, jvm 에게 중요한 것.
    private static final long serialVersionUID = 1L;

    private Map<String, String> errorMap;


    public CustomValidationApiException(String message) {
        super(message); // 부모 throwable 에 getMessage() 가 있음. 따라서 부모에게 보냄.
    }

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message); // 부모 throwable 에 getMessage() 가 있음. 따라서 부모에게 보냄.
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
