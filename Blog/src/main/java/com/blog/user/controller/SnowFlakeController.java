package com.blog.user.controller;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import javax.annotation.PostConstruct;

/**
 * SpringtBoot整合雪花算法 (基于hutool工具类)
 */
public class SnowFlakeController {
    /**
     * 工作机器ID(0~31)，2进制5位  32位减掉1位 31个
     */
    private long workerId = 0;
    /**
     * 数据中心ID(0~31)，2进制5位  32位减掉1位 31个
     */
    private long datacenterId = 1;

    /**
     * 雪花算法对象
     */
    private Snowflake snowFlake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            // 将网络ip转换成long
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取雪花ID，默认使用网络IP作为工作机器ID
     * @return ID
     */
    public synchronized long snowflakeId() {
        return this.snowFlake.nextId();
    }

    /**
     * 获取雪花ID
     * @param workerId 工作机器ID
     * @param datacenterId 数据中心ID
     * @return ID
     */
    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }
    //生成id
    public  Integer generateId(Long snowId){
        long id = snowId;//得到id 很长且高位很长部分是一样的数
        StringBuilder sb=new StringBuilder(id+"");
        StringBuilder reverse = sb.reverse();//将id翻转：我们发现id很长，且高位很长部分是一样的数
        id=new Long(reverse.toString())/1000;//切去部分长度
        while(id>1999999999){//1999999999以内的10位或9位或8位id;....
            id/=10;
        }
        Integer _id_ = Integer.parseInt(id+"");
        return _id_;
    }


}