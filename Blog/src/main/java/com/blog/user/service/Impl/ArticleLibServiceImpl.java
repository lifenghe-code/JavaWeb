package com.blog.user.service.Impl;
import com.blog.user.pojo.ArticleLib;
import com.blog.user.service.ArticleLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.user.dao.ArticleLibMapper;
import java.util.List;
@Service
public class ArticleLibServiceImpl implements ArticleLibService {
    @Autowired
    private ArticleLibMapper articleLibMapper;
    @Override
    public List<ArticleLib> findAll(){
        return articleLibMapper.selectAll();
    }
    @Override
    public ArticleLib findById(Long id){ return articleLibMapper.selectByPrimaryKey(id);}

    @Override
    public void add(ArticleLib articleLib){
        articleLibMapper.insertSelective(articleLib);
    }


    @Override
    public void update(ArticleLib articleLib) {
        articleLibMapper.updateByPrimaryKeySelective(articleLib);
    }


}
