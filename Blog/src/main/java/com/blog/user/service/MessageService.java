package com.blog.user.service;
import com.blog.user.pojo.Article;
import com.blog.user.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    void add(Message message);
    void update(Message message);
    List<Message> findByDate();

}
