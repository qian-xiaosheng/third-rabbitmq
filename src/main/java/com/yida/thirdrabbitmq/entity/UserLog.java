package com.yida.thirdrabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * (UserLog)实体类
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:04
 */
@Table(name = "user_log")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class UserLog implements Serializable {
    private static final long serialVersionUID = 652337246570262321L;

    @Id //指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")//针对mysql,获取插入后,主键的值
    //@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select s_user_seq.nextval from dual")//针对oracle,获取插入后,主键的值。前提是：需要在application.properties里配置mapper.before=true
    private Integer id;
    
    private String userName;
    
    private String module;
    
    private String operation;
    
    private String data;
    
    private Date createTime;

    public UserLog() {

    }

    public UserLog(String userName, String module, String operation, String data) {
        this.userName = userName;
        this.module = module;
        this.operation = operation;
        this.data = data;
        this.createTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", module='" + module + '\'' +
                ", operation='" + operation + '\'' +
                ", data='" + data + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}