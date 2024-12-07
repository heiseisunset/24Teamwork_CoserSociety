package com.example.demo_dzq.dto;

import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.pojo.OrganizerInfoPost;

import java.util.List;

public class ExhibitionZoneContentDTO {

    private List<User> organizers; // 角色为 "organizer" 的用户
    private List<OrganizerInfoPost> organizerInfoPosts; // 主办方信息发布表记录

    // Getter 和 Setter

    public List<User> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<User> organizers) {
        this.organizers = organizers;
    }

    public List<OrganizerInfoPost> getOrganizerInfoPosts() {
        return organizerInfoPosts;
    }

    public void setOrganizerInfoPosts(List<OrganizerInfoPost> organizerInfoPosts) {
        this.organizerInfoPosts = organizerInfoPosts;
    }
}
