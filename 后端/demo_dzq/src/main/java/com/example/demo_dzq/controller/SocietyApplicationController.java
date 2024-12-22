package com.example.demo_dzq.controller;

import com.example.demo_dzq.common.Response;
import com.example.demo_dzq.pojo.SocietyApplication;
import com.example.demo_dzq.service.SocietyApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/pending/{societyId}")
    public Response<List<SocietyApplication>> getPendingApplications(@PathVariable Integer societyId) {
        List<SocietyApplication> applications = applicationService.getPendingApplications(societyId);
        return new Response<>(200, "Pending applications retrieved.", applications);
    }

    @PostMapping("/approve")
    public Response<String> approveApplication(@RequestParam Integer applicationId,
                                               @RequestParam Integer societyId,
                                               @RequestParam Integer userId) {
        boolean isSuccess = applicationService.approveApplication(applicationId, societyId, userId);
        if (isSuccess) {
            return new Response<>(200, "Application approved successfully!", null);
        }
        return new Response<>(500, "Failed to approve application.", null);
    }

    @PostMapping("/reject")
    public Response<String> rejectApplication(@RequestParam Integer applicationId) {
        boolean isSuccess = applicationService.rejectApplication(applicationId);
        if (isSuccess) {
            return new Response<>(200, "Application rejected successfully!", null);
        }
        return new Response<>(500, "Failed to reject application.", null);
    }
}
