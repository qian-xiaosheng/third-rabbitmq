package com.yida.thirdrabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:04
 */
@Table(name = "user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    private static final long serialVersionUID = 243601720256621642L;

    @Id //指定主键
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")//针对mysql,获取插入后,主键的值
    //@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select s_user_seq.nextval from dual")//针对oracle,获取插入后,主键的值。前提是：需要在application.properties里配置mapper.before=true
    private Integer id;
    
    private String userName;
    
    private String password;
    
    private Integer isActive;
    
    private Date createTime;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                '}';
    }
}