package com.example.demo_dzq.controller;

import com.example.demo_dzq.service.SocietyService;
import com.example.demo_dzq.dto.SocietyDetailResponseDTO;  // 导入DTO类
import com.example.demo_dzq.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/societies")
public class SocietyController {

    @Autowired
    private SocietyService societyService;

    @GetMapping("/detail")
    public Response<SocietyDetailResponseDTO> getSocietyDetail(@RequestParam  Integer societyId) {
        try {
            // 调用服务层获取社团详细信息
            SocietyDetailResponseDTO response = societyService.getSocietyDetail(societyId);
            return new Response<>(200, "Success", response);
        } catch (Exception e) {
            return new Response<>(500, "Failed to fetch society details", null);
        }
    }
}
