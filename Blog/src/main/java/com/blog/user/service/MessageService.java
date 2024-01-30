package com.blog.user.service;
import com.blog.tools.Redis.RedisUtil;
import com.blog.user.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    RedisUtil findAllRedis();
    void add(Message message);
    void update(Message message);
    List<Message> findByDate();

}
