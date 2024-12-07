package com.example.demo_dzq.dto;

public class HelpRequestResponseDTO {

    private Integer requestId;

    // 构造方法
    public HelpRequestResponseDTO(Integer requestId) {
        this.requestId = requestId;
    }

    // Getter 和 Setter
    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
}
