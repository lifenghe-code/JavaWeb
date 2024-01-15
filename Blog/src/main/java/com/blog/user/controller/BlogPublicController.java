package com.blog.user.controller;

import com.blog.user.entity.Result;
import com.blog.user.entity.StatusCode;
import com.blog.user.pojo.ArticleLib;
import com.blog.user.pojo.User;
import com.blog.user.service.ArticleService;
import com.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.blog.user.pojo.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


@RestController
@RequestMapping(value = "/public")
public class BlogPublicController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public Result publicBlog(@RequestBody String userId){
        User us = userService.findById(Long.parseLong(userId));
        List<Article> articles = articleService.findByDate();
        Vector<Map> returnData = new Vector<>();
        for (Article value : articles) {
            Map<String, String> map = new HashMap<String, String>();
            Article article = articleService.findById(value.getId());
            map.put("ArticleId", value.getId().toString());
            map.put("Title", article.getTitle());
            map.put("Content", article.getContent());
            map.put("Date", article.getDate().toString());
            map.put("AuthorName", article.getAuthorName());
            map.put("AuthorId", article.getAuthorId().toString());
            map.put("Comments", article.getComments());
            returnData.add(map);
        }
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "查询成功",returnData);
    }
}
