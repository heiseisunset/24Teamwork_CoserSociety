package com.example.demo_dzq.dto;

public class PhotographyWorkResponseDTO {
    private int workId;

    // 构造方法
    public PhotographyWorkResponseDTO(int workId) {
        this.workId = workId;
    }

    // Getter 和 Setter
    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }
}
