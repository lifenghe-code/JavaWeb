package com.blog.user.service.Impl;

import com.blog.user.dao.ArticleMapper;
import com.blog.user.pojo.Article;
import com.blog.user.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public void add(Article article) {
        articleMapper.insertSelective(article);
    }

    @Override
    public Article findById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.updateByPrimaryKeySelective(article);
    }
    @Override
    public List<Article> findByDate(){
        return articleMapper.findByDate();
    }
    @Override
    public void delete(Article article){
        articleMapper.delete(article);
    }
}
