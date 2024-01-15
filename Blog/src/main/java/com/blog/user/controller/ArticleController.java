package com.blog.user.controller;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    private static final Long USERID = 11222L;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleLibService articleLibService;
    @Autowired
    private ArticleService articleService;


    @PostMapping(value = "/delete")
    public Result delete(@RequestBody String returnData){
        System.out.println(returnData);
        String articleId = returnData.split("=")[1];
        System.out.println(articleId);
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
    public Result comment(@RequestBody String data){
        System.out.println(data);
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "删除成功");

    }

}
