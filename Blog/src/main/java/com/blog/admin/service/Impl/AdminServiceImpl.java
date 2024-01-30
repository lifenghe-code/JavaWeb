package com.blog.admin.service.Impl;
import org.springframework.data.redis.core.RedisTemplate;
import com.blog.admin.dao.AdminMapper;
import com.blog.admin.pojo.Admin;
import com.blog.admin.service.AdminService;
import com.blog.tools.Redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String, List<Admin>> redisTemplate;
    @Override
    public List<Admin> selectAll() {
        // 初级版本，设置过期时间为5s
        List<Admin> adminList = redisTemplate.boundValueOps("adminList").get();
        //2、缓存没有查询数据库，并把数据加入缓存
        if(adminList == null || adminList.size() < 1){
            adminList = adminMapper.selectAll();
            //把数据加入缓存，设置有效时间为5秒
            redisTemplate.boundValueOps("adminList").set(adminList,5,TimeUnit.SECONDS);
        }else{
            System.out.println("从缓存中读取了用户列表...");
        }
        return adminList;
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public Admin findByAdmin(Admin admin) {
        return null;
    }

    @Override
    public void add(Admin admin) {

    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public void delete(Long id) {

    }
}
