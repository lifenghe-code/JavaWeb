package com.blog.admin.service.Impl;
import com.blog.admin.dao.OmnibusMapper;
import com.blog.admin.service.OmnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class OmnibusServiceImpl implements OmnibusService {
    @Autowired
    private OmnibusMapper omnibusMapper;
    @Override
    public Integer countUser() {
        return omnibusMapper.countUser();
    }

    @Override
    public Integer countArticle() {
        return omnibusMapper.countArticle();
    }
}
