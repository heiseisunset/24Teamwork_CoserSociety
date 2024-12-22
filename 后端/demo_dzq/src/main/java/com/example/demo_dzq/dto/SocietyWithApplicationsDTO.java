package com.example.demo_dzq.dto;

import java.util.List;

public class SocietyWithApplicationsDTO {

    private List<SocietyMemberWithUserDTO> societyMembers;
    private List<SocietyApplicationWithUserDTO> societyApplications;

    // 无参构造函数
    public SocietyWithApplicationsDTO() {
    }

    // 全参构造函数
    public SocietyWithApplicationsDTO(List<SocietyMemberWithUserDTO> societyMembers, List<SocietyApplicationWithUserDTO> societyApplications) {
        this.societyMembers = societyMembers;
        this.societyApplications = societyApplications;
    }

    // Getter 和 Setter
    public List<SocietyMemberWithUserDTO> getSocietyMembers() {
        return societyMembers;
    }

    public void setSocietyMembers(List<SocietyMemberWithUserDTO> societyMembers) {
        this.societyMembers = societyMembers;
    }

    public List<SocietyApplicationWithUserDTO> getSocietyApplications() {
        return societyApplications;
    }

    public void setSocietyApplications(List<SocietyApplicationWithUserDTO> societyApplications) {
        this.societyApplications = societyApplications;
    }
}
