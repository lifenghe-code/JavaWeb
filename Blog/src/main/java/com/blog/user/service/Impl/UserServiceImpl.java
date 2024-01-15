package com.blog.user.service.Impl;
import com.blog.user.pojo.User;
import com.blog.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private com.blog.user.dao.UserMapper UserMapper;
    @Override
    public List<User> findAll(){
        return UserMapper.selectAll();
    }
    @Override
    public User findById(Long id){ return UserMapper.selectByPrimaryKey(id);}

    @Override
    public User findByUser(User user) {
        return UserMapper.findByUser(user);
    }

    @Override
    public void add(User user) {
        UserMapper.insertSelective(user);
    }
    @Override
    public void update(User user) {
        UserMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    public void delete(Long id) {
        UserMapper.deleteByPrimaryKey(id);
    }

    public List<User> findList(User user) {
        return null;
    }

}
