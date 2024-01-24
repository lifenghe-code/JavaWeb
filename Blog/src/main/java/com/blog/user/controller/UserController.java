package com.blog.user.controller;
import com.alibaba.fastjson.JSON;
import com.blog.user.entity.Result;
import com.blog.user.entity.StatusCode;
import com.blog.user.pojo.ArticleLib;
import com.blog.user.pojo.Article;
import com.blog.user.pojo.User;
import com.blog.user.service.ArticleService;
import com.blog.user.service.UserService;
import com.blog.user.service.ArticleLibService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.Date;
import java.util.*;
import java.io.IOException;
import java.sql.Timestamp;
@CrossOrigin
@JsonIgnoreProperties(value = {"handler"})
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Long USERID;

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleLibService articleLibService;
    @Autowired
    private ArticleService articleService;
    @Resource
    private HttpServletRequest request;


    //@Autowired
    @Resource
    private HttpServletResponse response;


    /***
     * 查询全部数据
     * @return
     */
    public void forwardToPage(@org.jetbrains.annotations.NotNull HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException{
        RequestDispatcher dispatccher = request.getRequestDispatcher(page);
        dispatccher.forward(request,response);
    }
    @GetMapping
    public Result<List<User>> findAll(){
        List<User> userList = userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK,"查询成功",userList);
    }
    @GetMapping("/{Id}")
    public Result<User> findById(@PathVariable Long Id){
        //调用BrandService实现根据主键查询Brand
        User user = userService.findById(Id);
        return new Result<User>(true,StatusCode.OK,"查询成功",user);
    }
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }
    @PostMapping("/update")
    public Result update(@RequestBody User user) {

        userService.update(user);
        return new Result(true, StatusCode.OK, "更新成功");
    }
    @DeleteMapping(value="/{id}")
    public Result delete(@PathVariable Long id){
        //设置主键值
        userService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
    @PostMapping(value = "/search" )
    public Result<List<User>> findList(@RequestBody(required = false) User user) {
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping(value = "/login")
    // 前端发送form表单类型的数据时，注解采用RequestParm；若采用Json类型时采用RequestBody类型数据
    public Result login( String Name, String PassWord) throws ServletException, IOException {
        if(StringUtils.isEmpty(Name)){
            return new Result<List<User>>(false, StatusCode.ERROR, "请填写用户名");
        }
        if(StringUtils.isEmpty(PassWord)){
            return new Result<List<User>>(false, StatusCode.ERROR, "请填写密码");
        }
        //数据库校验
        User user = new User();
        user.setPassWord(PassWord);
        user.setName(Name);
        User us = userService.findByUser(user);
        if(StringUtils.isEmpty(us)){
            return new Result<List<User>>(false, StatusCode.ERROR, "账号或密码错误");
        }
        else{
            USERID = us.getId();
            System.out.println("UserID:"+USERID);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",USERID.toString());
            String returnData = jsonObject.toString();
            System.out.println(returnData);
            return new Result<List<User>>(true, StatusCode.OK, "登录成功",returnData);
        }
    }

    @GetMapping("/login")
    public String blogDetail(){
        return "/blog-detail";
    }

    @PostMapping(value = "/register")
    public Result register(String Name, String PassWord){
        User user = new User();
        user.setPassWord(PassWord);
        user.setName(Name);
        User us = userService.findByUser(user);
        if(StringUtils.isEmpty(us)){
            SnowFlakeController snowFlake = new SnowFlakeController();
            user.setId(snowFlake.snowflakeId());
            long ArticleLibId =  snowFlake.snowflakeId();
            ArticleLib articleLib = new ArticleLib();
            articleLib.setArticleLibId(ArticleLibId);
            articleLibService.add(articleLib);
            user.setArticleLibId(ArticleLibId);
            userService.add(user);
            return new Result<List<User>>(true, StatusCode.OK, "注册成功");
        }
        else{
            return new Result<List<User>>(false, StatusCode.ERROR, "用户名存在，请重新输入");
        }
    }


    @PostMapping(value = "/edit")  //先实现文字功能
    public Result edit(String Title, String Input, String UserId){

        if(StringUtils.isEmpty(Title)){
            return new Result<List<ArticleLib>>(false, StatusCode.ERROR, "添加失败，标题为空");
        }
        else{
            User us = userService.findById(Long.parseLong(UserId));
            Article article = new Article();
            SnowFlakeController snowFlake = new SnowFlakeController();
            Long articleId = snowFlake.snowflakeId();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            article.setId(articleId);
            article.setTitle(Title);
            article.setContent(Input);
            article.setTime(currentTime);
            article.setAuthorName(us.getName());
            article.setAuthorId(us.getId());
            articleService.add(article);
            Long articleLibId = us.getArticleLibId();
            ArticleLib articleLib = articleLibService.findById(articleLibId);
            System.out.println("articleLibId:"+articleLib.getArticleIds());
            articleLib.addArticleId(articleId);
            articleLibService.update(articleLib);
            return new Result<List<ArticleLib>>(true, StatusCode.OK, "提交成功");
        }
    }


    @PostMapping(value = "/list")
    public Result list(@RequestBody String userId){
        User us = userService.findById(Long.parseLong(userId));
        Long articleLibId = us.getArticleLibId();
        System.out.println("ArticleLibID:"+articleLibId);
        ArticleLib articleLib = articleLibService.findById(articleLibId);

        if(articleLib.getArticleIds() == null){
            Vector<Map> returnData = new Vector<>();
            return new Result<List<ArticleLib>>(true, StatusCode.OK, "查询成功",returnData);
        }
        // String NewArticleId = articleId.substring(1);
        String articleIds = articleLib.getArticleIds();
        if(articleIds.isEmpty()){
            return new Result<List<ArticleLib>>(true, StatusCode.OK, "文章列表为空");
        }
        String[] tmp = articleIds.split(",");
        Vector<Long> atrticleIds = new Vector<>(); //存储文章的ID
        for(int i = 0;i < tmp.length; i++){
            atrticleIds.add(Long.parseLong(tmp[i]));
        }

        Vector<Map> returnData = new Vector<>();
        Map<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("userName",us.getName());
        System.out.println(us.getName());
        returnData.add(userInfo);
        for(int i = 0; i<atrticleIds.size(); i++){
            Map<String, String> map = new HashMap<String, String>();
            Article article = articleService.findById(atrticleIds.get(i));
            map.put("ArticleId", atrticleIds.get(i).toString());
            map.put("Title", article.getTitle());
            map.put("Content", article.getContent());
            map.put("Date", article.getDate().toString());
            map.put("AuthorName", article.getAuthorName());
            map.put("AuthorId", article.getAuthorId().toString());
            map.put("Comments", article.getComments());
            returnData.add(map);
        }
        // String returnData = JSON.toJSONString(map);
        // System.out.println("Article:"+returnData);
        //System.out.println(returnData);
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "查询成功",returnData);
    }

    @PostMapping(value = "/all")
    public Result all(@RequestBody String userId){
        User us = userService.findById(Long.valueOf(userId));
        Long articleLibId = us.getArticleLibId();
        ArticleLib articleLib = articleLibService.findById(articleLibId);
        String ids = articleLib.getArticleIds();
        String[] tmp = ids.split(",");
        Vector<Long> atrticleIds = new Vector<>(); //存储文章的ID
        for (String s : tmp) {
            atrticleIds.add(Long.parseLong(s));
        }
        Vector<Map> returnData = new Vector<>();
        Map<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("userName",us.getName());
        //System.out.println(us.getName());
        returnData.add(userInfo);
        for(int i = 0; i<atrticleIds.size(); i++){
            Map<String, String> map = new HashMap<String, String>();
            Article article = articleService.findById(atrticleIds.get(i));
            map.put("Title", article.getTitle());
            map.put("Content", article.getContent());
            System.out.println(article.getDate().toString());
            map.put("Date", article.getDate().toString());
            returnData.add(map);
        }

        //System.out.println(returnData);
        return new Result<List<ArticleLib>>(true, StatusCode.OK, "查询成功",returnData);
    }
}
