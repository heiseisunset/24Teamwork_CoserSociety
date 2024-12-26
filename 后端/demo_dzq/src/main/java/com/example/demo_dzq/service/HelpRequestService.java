package com.example.demo_dzq.service;

import com.example.demo_dzq.pojo.HelpRequest;

import java.util.List;

public interface HelpRequestService {

    int addHelpRequest(HelpRequest helpRequest) throws Exception;

    public List<HelpRequest> getAllHelpRequests();

    // 获取当前用户发布的所有求助请求
    List<HelpRequest> getHelpRequestsByUserId(Integer userId);

    boolean markAsResolved(Integer requestId) throws Exception;

    boolean markAsUnresolved(Integer requestId) throws Exception;

}
