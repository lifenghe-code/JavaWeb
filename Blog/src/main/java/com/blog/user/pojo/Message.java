package com.blog.user.pojo;
import javax.persistence.*;
import java.sql.Timestamp;
@Table(name = "message")
// 聊天记录-消息内容
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "SendUserId")
    private String sendUserId; // 推送方用户id
    @Column(name = "ReceiveUserId")
    private String receiveUserId; // 接收方用户id
    @Column(name = "SendUserName")
    private String sendUserName; // 用户名
    @Column(name = "ReceiveUserName")
    private String receiveUserName; // 用户名
    @Column(name = "MesgText")
    private String mesgText; // 消息内容
    @Column(name = "CreateTime")
    private Timestamp createTime; // 创建时间
    @Column(name = "Status")
    private Boolean status; // 消息状态

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getMesgText() {
        return mesgText;
    }

    public void setMesgText(String mesgText) {
        this.mesgText = mesgText;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}