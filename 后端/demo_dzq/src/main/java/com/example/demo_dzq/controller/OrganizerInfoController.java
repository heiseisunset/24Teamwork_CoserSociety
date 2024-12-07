package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.OrganizerInfoDTO;
import com.example.demo_dzq.service.OrganizerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizer")
public class OrganizerInfoController {

    @Autowired
    private OrganizerInfoService organizerInfoService;

    // 创建或更新主办方信息
    @PostMapping("/createOrUpdate")
    public Response<String> createOrUpdateOrganizerInfo(@RequestBody OrganizerInfoDTO organizerInfoDTO) {
        try {
            organizerInfoService.createOrUpdateOrganizerInfo(organizerInfoDTO);
            // 返回成功的响应，data 为 null
            return new Response<>(200, "Organizer information created or updated successfully", null);
        } catch (Exception e) {
            // 返回错误响应
            return new Response<>(500, "Organizer information created or updated unsuccessfully" , null);
        }
    }
}
