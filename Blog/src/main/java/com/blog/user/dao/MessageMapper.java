package com.blog.user.dao;

import com.blog.user.pojo.Article;
import com.blog.user.pojo.ArticleLib;
import com.blog.user.pojo.Message;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MessageMapper extends Mapper<Message> {
    List<Message> findByDate();
}
