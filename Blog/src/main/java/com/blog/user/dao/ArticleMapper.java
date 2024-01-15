package com.blog.user.dao;
import com.blog.user.pojo.Article;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ArticleMapper extends Mapper<Article>{
    List<Article> findByDate();
}

