package com.example.demo_dzq.dto;

import com.example.demo_dzq.pojo.CoserWork;
import com.example.demo_dzq.pojo.User;
import com.example.demo_dzq.pojo.Comment;

import java.util.List;

public class CoserWorkDetailDTO {
    private User user;
    private CoserWork coserWork;
    private List<Comment> comments;

    public CoserWorkDetailDTO(User user, CoserWork coserWork, List<Comment> comments) {
        this.user = user;
        this.coserWork = coserWork;
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CoserWork getCoserWork() {
        return coserWork;
    }

    public void setCoserWork(CoserWork coserWork) {
        this.coserWork = coserWork;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
