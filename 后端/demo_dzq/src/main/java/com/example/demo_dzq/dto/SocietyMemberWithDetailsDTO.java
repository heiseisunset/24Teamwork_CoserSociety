package com.example.demo_dzq.dto;

import com.example.demo_dzq.pojo.Society;
import com.example.demo_dzq.pojo.SocietyMember;

import java.util.List;

public class SocietyMemberWithDetailsDTO {

    private List<Society> society;  // 修改为 List<Society> 类型
    private List<SocietyMember> societyMembers;

    // Getter 和 Setter

    public List<Society> getSociety() {
        return society;
    }

    public void setSociety(List<Society> society) {
        this.society = society;
    }

    public List<SocietyMember> getSocietyMembers() {
        return societyMembers;
    }

    public void setSocietyMembers(List<SocietyMember> societyMembers) {
        this.societyMembers = societyMembers;
    }
}
