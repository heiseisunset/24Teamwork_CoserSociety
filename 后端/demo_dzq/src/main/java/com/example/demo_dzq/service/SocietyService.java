package com.example.demo_dzq.service;

import com.example.demo_dzq.dto.SocietyDetailResponseDTO;
import com.example.demo_dzq.pojo.Society;
import com.example.demo_dzq.pojo.SocietyMember;
import com.example.demo_dzq.pojo.User;
import java.util.List;

public interface SocietyService {
    Society getSocietyById(Integer societyId);
    List<SocietyMember> getSocietyMembersBySocietyId(Integer societyId);
    User getUserById(Integer userId);
    public SocietyDetailResponseDTO getSocietyDetail(Integer societyId);
    boolean createSocietyWithFounderNameAndAddMember(Society society);
}
