package com.example.demo_dzq.service;

import com.example.demo_dzq.dto.SocietyApplicationWithUserDTO;
import com.example.demo_dzq.pojo.SocietyApplication;

import java.util.List;

public interface SocietyApplicationService {
    boolean submitApplication(SocietyApplication application);

    List<SocietyApplication> getPendingApplications(Integer societyId);

    boolean approveApplication(Integer applicationId, Integer societyId, Integer userId);

    boolean rejectApplication(Integer applicationId);

    List<SocietyApplicationWithUserDTO> getPendingApplicationsWithUserBySocietyId(Integer societyId);
}

