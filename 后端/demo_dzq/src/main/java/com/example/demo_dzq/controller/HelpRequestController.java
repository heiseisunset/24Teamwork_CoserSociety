package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.HelpRequestResponseDTO;
import com.example.demo_dzq.dto.RequestIdDTO;
import com.example.demo_dzq.pojo.HelpRequest;
import com.example.demo_dzq.service.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/helprequests")
public class HelpRequestController {

    @Autowired
    private HelpRequestService helpRequestService;

    // 创建求助请求
    @PostMapping("/post")
    public Response<HelpRequestResponseDTO> addHelpRequest(
            @RequestParam("file") MultipartFile file,  // 上传图片文件
            @RequestParam("userId") Integer userId,
            @RequestParam("title") String title,
            @RequestParam("eventDate") String eventDate,
            @RequestParam("duration") String duration,
            @RequestParam("description") String description,
            @RequestParam("phone") String phone,
            @RequestParam("contact") String contact,
            @RequestParam("fee") String fee,
            @RequestParam("city") String city) {
        try {
            // 将 String 类型的 eventDate 转换为 LocalDate 类型
            LocalDate eventDateParsed = LocalDate.parse(eventDate, DateTimeFormatter.ISO_DATE);

            // 将 String 类型的 duration 转换为 Integer 类型
            Integer durationParsed = Integer.parseInt(duration);  // 可能会抛出 NumberFormatException, 需要检查输入是否合法

            // 将 String 类型的 fee 转换为 BigDecimal 类型
            BigDecimal feeParsed = new BigDecimal(fee);  // 可能会抛出 NumberFormatException, 需要检查输入是否合法

            // 确保目录存在
            Path uploadDir = Paths.get("D:/icosImage");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // 创建目录
            }

            // 获取文件扩展名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName != null ? originalFileName.substring(originalFileName.lastIndexOf(".")) : ".jpg";

            // 生成唯一的文件名，避免文件名冲突
            String newFileName = System.currentTimeMillis() + fileExtension;

            // 目标路径
            Path targetPath = uploadDir.resolve(newFileName);

            // 保存文件到磁盘
            file.transferTo(targetPath);

            // 构造文件的访问URL (假设你的应用能够通过 HTTP 访问 D:/icosImage)
            String imageUrl = "D:/icosImage/" + newFileName; // 根据你项目的实际访问路径调整

            HelpRequest helpRequest = new HelpRequest();

            helpRequest.setUserId(userId);
            helpRequest.setTitle(title);
            helpRequest.setEventDate(eventDateParsed);
            helpRequest.setDuration(durationParsed);
            helpRequest.setDescription(description);
            helpRequest.setPhone(phone);
            helpRequest.setContact(contact);
            helpRequest.setFee(feeParsed);
            helpRequest.setCity(city);
            helpRequest.setImageUrl(imageUrl);

            // 明确设置为未解决
            helpRequest.setIsResolved(false);
            int requestId = helpRequestService.addHelpRequest(helpRequest);
            HelpRequestResponseDTO responseDTO = new HelpRequestResponseDTO(requestId);
            return new Response<>(200, "求助发布成功", responseDTO);
        } catch (Exception e) {
            return new Response<>(500, "求助发布失败"+ e.getMessage(), null);
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

    @PutMapping("/resolve")
    public Response<Void> markAsResolved(@RequestBody RequestIdDTO requestIdDTO) {
        try {
            boolean result = helpRequestService.markAsResolved(requestIdDTO.getRequestId());
            if (result) {
                return new Response<>(200, "求助设置为已解决成功", null);
            } else {
                return new Response<>(404, "未找到对应的求助请求", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "操作失败: " + e.getMessage(), null);
        }
    }

    @PutMapping("/unresolve")
    public Response<Void> markAsUnresolved(@RequestBody RequestIdDTO requestIdDTO) {
        try {
            boolean result = helpRequestService.markAsUnresolved(requestIdDTO.getRequestId());
            if (result) {
                return new Response<>(200, "求助设置为未解决成功", null);
            } else {
                return new Response<>(404, "未找到对应的求助请求", null);
            }
        } catch (Exception e) {
            return new Response<>(500, "操作失败: " + e.getMessage(), null);
        }
    }

}
