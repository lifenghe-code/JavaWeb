package com.blog.admin.service;

import com.blog.admin.pojo.Admin;
import com.blog.user.pojo.User;

import java.util.List;

public interface AdminService {
    List<Admin> selectAll();
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Admin findById(Long id);
    Admin findByAdmin(Admin admin);
    // 增加商品
    void add(Admin admin);
    //修改品牌
    void update(Admin admin);
    //删除品牌
    void delete(Long id);
}
