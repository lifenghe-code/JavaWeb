package com.blog.user.service;

import com.blog.user.pojo.ArticleLib;

import java.util.List;

public interface ArticleLibService {
    List<ArticleLib> findAll();
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ArticleLib findById(Long id);
    void add(ArticleLib articleLib);


    void update(ArticleLib articleLib);
    //删除品牌

}
