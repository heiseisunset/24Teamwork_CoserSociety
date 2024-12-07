package com.example.demo_dzq.service;

import com.example.demo_dzq.dto.ExhibitionZoneContentDTO;
import com.example.demo_dzq.pojo.OrganizerInfoPost;
import com.example.demo_dzq.pojo.User;

import java.util.List;

public interface ExhibitionZoneContentService {

    ExhibitionZoneContentDTO getExhibitionZoneContent();

    public List<User> getOrganizers();

    public List<OrganizerInfoPost> getOrganizerInfoPosts();
}
