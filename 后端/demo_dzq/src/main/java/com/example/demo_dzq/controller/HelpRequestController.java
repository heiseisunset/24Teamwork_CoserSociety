package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.HelpRequestResponseDTO;
import com.example.demo_dzq.pojo.HelpRequest;
import com.example.demo_dzq.service.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/helprequests")
public class HelpRequestController {

    @Autowired
    private HelpRequestService helpRequestService;

    // 创建求助请求
    @PostMapping("/post")
    public Response<HelpRequestResponseDTO> addHelpRequest(@RequestBody HelpRequest helpRequest) {
        try {
            int requestId = helpRequestService.addHelpRequest(helpRequest);
            HelpRequestResponseDTO responseDTO = new HelpRequestResponseDTO(requestId);
            return new Response<>(200, "求助发布成功", responseDTO);
        } catch (Exception e) {
            return new Response<>(500, "求助发布失败", null);
        }
    }

    // 查看当前登录用户发布的所有求助
    @GetMapping("/user")
    public Response<List<HelpRequest>> getHelpRequestsByUserId(@RequestParam Integer userId) {
        try {
            // 调用 Service 获取该用户发布的所有求助
            List<HelpRequest> helpRequests = helpRequestService.getHelpRequestsByUserId(userId);

            // 如果没有找到任何求助请求，返回404
            if (helpRequests.isEmpty()) {
                return new Response<>(404, "没有找到该用户发布的求助", null);
            }

            // 返回成功响应
            return new Response<>(200, "获取求助列表成功", helpRequests);
        } catch (Exception e) {
            return new Response<>(500, "获取求助列表失败: " + e.getMessage(), null);
        }
    }
}
