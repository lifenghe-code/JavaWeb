package com.blog.user.controller;
import com.alibaba.fastjson.JSONObject;
import com.blog.user.entity.Result;
import com.blog.user.entity.StatusCode;
import com.blog.user.pojo.Article;
import com.blog.user.pojo.ArticleLib;
import com.blog.user.pojo.User;
import com.blog.user.service.ArticleLibService;
import com.blog.user.service.ArticleService;
import com.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping(value = "/article")
public class ArticleController {


    @Autowired
    private UserService userService;
    @Autowired
    private ArticleLibService articleLibService;
    @Autowired
    private ArticleService articleService;


    @PostMapping(value = "/delete")
    public Result delete(@RequestBody String returnData){
        Long USERID = 11222L;
        String articleId = returnData.split("=")[1];
        User us = userService.findById(USERID);
        Long articleLibId = us.getArticleLibId();
        ArticleLib articleLib = articleLibService.findById(articleLibId);
        String[] tmp = articleLib.getArticleIds().split(",");
        StringBuilder ids = new StringBuilder();
        for (String s : tmp) {
            if (s.equals(articleId)){

            }
            else{
                ids.append(",").append(s);
            }
        }
        if(ids.length() == 0){

        }
        else{
            ids = new StringBuilder(ids.substring(1));
        }
        articleLib.setIds(String.valueOf(ids));
        articleLibService.update(articleLib);
        articleService.delete(articleService.findById(Long.valueOf(articleId)));
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "删除成功");
    }
    @PostMapping(value = "/comment")
    public Result comment(@RequestBody String returnData) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(returnData);

        Long userId = Long.parseLong(jsonObject.get("userId").toString());
        Long articleId = Long.parseLong(jsonObject.get("articleId").toString());
        String comment = jsonObject.get("comment").toString();
        String date = jsonObject.get("date").toString();
        JSONObject commentData = new JSONObject();
        // HashMap<String, String> commentData = new HashMap<String, String>();
        User us = userService.findById(userId);
        commentData.put("userId",us.getId().toString());
        commentData.put("userName",us.getName());
        commentData.put("date",date);
        commentData.put("comment",comment);
        Article ar = articleService.findById(articleId);
        if(ar.getComments()==null){
            ar.setComments(commentData.toString());
        }
        else {
            ar.setComments(ar.getComments() + "," + commentData.toString());
        }
        articleService.update(ar);
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "添加评论成功", commentData);

    }
    @PostMapping(value = "/showComments")
    public Result showComments(@RequestBody String returnData) throws ParseException {
        System.out.println(returnData);
        JSONObject jsonObject = JSONObject.parseObject(returnData);
        System.out.println(jsonObject);
        Long userId = Long.parseLong(jsonObject.get("userId").toString());
        Long articleId = Long.parseLong(jsonObject.get("articleId").toString());
        JSONObject commentData = new JSONObject();
        // HashMap<String, String> commentData = new HashMap<String, String>();
        User us = userService.findById(userId);
        Article ar = articleService.findById(articleId);
        commentData.put("userId",us.getId().toString());
        commentData.put("userName",us.getName());
        commentData.put("articleId",ar.getId().toString());
        commentData.put("articleTitle",ar.getTitle());
        commentData.put("authorId",ar.getAuthorId().toString());
        commentData.put("authorName",ar.getAuthorName());
        commentData.put("date",ar.getDate().toString());
        commentData.put("content", ar.getContent());
        commentData.put("comments", ar.getComments());
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "更新评论成功", commentData);

    }
}
