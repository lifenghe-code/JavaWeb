package com.blog.user.service.Impl;
import com.blog.user.dao.ArticleMapper;
import com.blog.user.pojo.Message;
import com.blog.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import com.blog.user.dao.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;

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
}
