package com.blog.user.dao;
import com.blog.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {
    User findByUser(User user);
}
