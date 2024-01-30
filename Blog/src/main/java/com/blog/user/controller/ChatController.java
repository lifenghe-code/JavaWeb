package com.blog.user.controller;
import com.alibaba.fastjson.JSONObject;
import com.blog.tools.Redis.RedisUtil;
import com.blog.user.entity.Result;
import com.blog.user.entity.StatusCode;
import com.blog.user.pojo.ArticleLib;
import com.blog.user.pojo.Message;
import com.blog.user.pojo.User;
import com.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.user.service.MessageService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/chat")
public class ChatController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @PostMapping("/message")
    public Result message()throws ParseException {
        List<Message> Data = messageService.findByDate();
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "获取消息成功",Data);
    }
    @PostMapping("/send")
    public Result chat(@RequestBody String returnData) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(returnData);
        Message message = new Message();
        Long userId = Long.parseLong(jsonObject.get("userId").toString());
        User user = userService.findById(userId);
        SnowFlakeController snowFlake = new SnowFlakeController();
        Integer messageId = snowFlake.generateId(snowFlake.snowflakeId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(jsonObject.get("date").toString());
        Timestamp timestamp = new Timestamp(date.getTime());
        message.setId(messageId);
        message.setCreateTime(timestamp);
        message.setSendUserId(String.valueOf(userId));
        message.setSendUserName(user.getName());
        message.setMesgText((String) jsonObject.get("mesg"));
        message.setStatus(true);
        messageService.add(message);
        List<Message> Data = messageService.findByDate();
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "消息发送成功",Data);
    }
    @PostMapping("/messageRedis")
    public Result messageRedis() {
        RedisUtil Data = messageService.findAllRedis();
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "获取消息成功",Data.getAll());
    }
}
