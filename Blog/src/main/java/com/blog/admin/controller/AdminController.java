package com.blog.admin.controller;
import com.alibaba.fastjson.JSONObject;
import com.blog.admin.entity.Result;
import com.blog.user.entity.StatusCode;
import com.blog.user.pojo.Article;
import com.blog.user.pojo.User;
import com.blog.user.service.ArticleService;
import com.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @PostMapping(value = "/allUsers")
    public Result allUsers(){
        List<User> data =  userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", data);

    }
    @PostMapping(value = "/allArticles")
    public Result allArticles(){
        List<Article> data =  articleService.findAll();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", data);

    }
    @PostMapping(value = "/comments")
    public Result connemts(@RequestBody String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        Long userId = Long.parseLong(jsonObject.get("userId").toString());
        Long articleId = Long.parseLong(jsonObject.get("articleId").toString());
        String comment = jsonObject.get("comment").toString();
        String date = jsonObject.get("date").toString();
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", data);

    }
}
