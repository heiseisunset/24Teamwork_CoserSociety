package com.example.demo_dzq.service.impl;

import com.example.demo_dzq.mapper.HelpRequestMapper;
import com.example.demo_dzq.pojo.HelpRequest;
import com.example.demo_dzq.service.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpRequestServiceImpl implements HelpRequestService {

    @Autowired
    private HelpRequestMapper helpRequestMapper;

    @Override
    public int addHelpRequest(HelpRequest helpRequest) throws Exception {
        // 插入数据
        helpRequestMapper.insertHelpRequest(helpRequest);

        // 获取插入后的 ID
        return helpRequestMapper.getLastInsertId();  // 返回最新插入的 requestId
    }

    public List<HelpRequest> getAllHelpRequests() {
        return helpRequestMapper.selectAllHelpRequests();
    }

    @Override
    public List<HelpRequest> getHelpRequestsByUserId(Integer userId) {
        // 调用 Mapper 获取该用户发布的所有求助请求
        return helpRequestMapper.selectHelpRequestsByUserId(userId);
    }

    @Override
    public boolean markAsResolved(Integer requestId) throws Exception {
        int updatedRows = helpRequestMapper.markAsResolved(requestId);
        return updatedRows > 0; // 返回是否更新成功
    }

    @Override
    public boolean markAsUnresolved(Integer requestId) throws Exception {
        int updatedRows = helpRequestMapper.markAsUnresolved(requestId);
        return updatedRows > 0; // 返回是否更新成功
    }
}

