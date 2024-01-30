package com.blog.user.service.Impl;
import com.blog.tools.Redis.RedisUtil;
import com.blog.user.pojo.Message;
import com.blog.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import com.blog.user.dao.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public List<Message> findAll() {
        return messageMapper.selectAll();
    }

    @Override
    public void add(Message message) {
        messageMapper.insertSelective(message);
    }

    @Override
    public void update(Message message) {
        messageMapper.updateByPrimaryKeySelective(message);
    }
    @Override
    public List<Message> findByDate(){
        return messageMapper.findByDate();
    }


    @Override
    public RedisUtil findAllRedis() {
        List<Message> result = messageMapper.findByDate();
        int num = 1;
        for (Message message : result) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", message.getId().toString());
            data.put("sendUserId", message.getSendUserId());
            data.put("sendUserName", message.getSendUserName());
            data.put("mesgText", message.getMesgText());
            data.put("createTime", message.getCreateTime().toString());
            data.put("status", message.getStatus().toString());
            redisUtil.set(Integer.toString(num), data.toString());
            num++;
        }
        return redisUtil;
    }
}
