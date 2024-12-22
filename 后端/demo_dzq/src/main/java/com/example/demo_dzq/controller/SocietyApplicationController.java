package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.dto.ApprovalRequestDTO;
import com.example.demo_dzq.dto.RejectApplicationRequestDTO;
import com.example.demo_dzq.pojo.SocietyApplication;
import com.example.demo_dzq.service.SocietyApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class SocietyApplicationController {

    @Autowired
    private SocietyApplicationService applicationService;

    @PostMapping("/submit")
    public Response<String> submitApplication(@RequestBody SocietyApplication application) {
        boolean isSuccess = applicationService.submitApplication(application);
        if (isSuccess) {
            return new Response<>(200, "Application submitted successfully!", null);
        }
        return new Response<>(500, "Failed to submit application.", null);
    }

    @GetMapping("/pending")
    public Response<List<SocietyApplication>> getPendingApplications(@RequestParam Integer societyId) {
        List<SocietyApplication> applications = applicationService.getPendingApplications(societyId);
        return new Response<>(200, "Pending applications retrieved.", applications);
    }

    @PostMapping("/approve")
    public Response<String> approveApplication(@RequestBody ApprovalRequestDTO approvalRequest) {
        try {
            // 从请求体中获取数据
            Integer applicationId = approvalRequest.getApplicationId();
            Integer societyId = approvalRequest.getSocietyId();
            Integer userId = approvalRequest.getUserId();

            boolean isSuccess = applicationService.approveApplication(applicationId, societyId, userId);
            if (isSuccess) {
                return new Response<>(200, "Application approved successfully!", null);
            } else {
                return new Response<>(500, "Failed to approve application. The application might have been already processed or does not exist.", null);
            }
        } catch (IllegalArgumentException e) {
            return new Response<>(800, "Invalid input: " + e.getMessage(), null);
        } catch (DataAccessException e) {
            return new Response<>(500, "Database error: " + e.getMessage(), null);
        } catch (Exception e) {
            return new Response<>(500, "Unexpected error occurred: " + e.getMessage(), null);
        }
    }


    @PostMapping("/reject")
    public Response<String> rejectApplication(@RequestBody RejectApplicationRequestDTO request) {
        boolean isSuccess = applicationService.rejectApplication(request.getApplicationId());
        if (isSuccess) {
            return new Response<>(200, "Application rejected successfully!", null);
        }
        return new Response<>(500, "Failed to reject application.", null);
    }
}
