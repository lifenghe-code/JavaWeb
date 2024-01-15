package com.blog.user.service;

import com.blog.user.pojo.Article;
import com.blog.user.pojo.ArticleLib;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();
    void add(Article article);
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Article findById(Long id);

    // 增加商品
    //修改品牌
    void update(Article article);
    //删除品牌
    List<Article> findByDate();
    void delete(Article article);
}
