package com.blog.user.service;

import com.blog.user.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    User findById(Long id);
    User findByUser(User user);
    // 增加商品
    void add(User user);
    //修改品牌
    void update(User user);
    //删除品牌
    void delete(Long id);
    List<User> findList(User user);
}
