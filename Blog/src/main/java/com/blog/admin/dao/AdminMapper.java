package com.blog.admin.dao;
import com.blog.admin.pojo.Admin;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface AdminMapper  extends Mapper<Admin> {
    Admin findAdminByName(String name);
}
