package com.yida.thirdrabbitmq.service.impl;

import com.yida.thirdrabbitmq.entity.User;
import com.yida.thirdrabbitmq.dao.UserDao;
import com.yida.thirdrabbitmq.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:04
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User selectByUserNamePassword(String user, String password) {
        Example example = new Example(User.class); //创建条件
        Example.Criteria ca = example.createCriteria(); //添加条件
        ca.andEqualTo("userName", user);
        ca.andEqualTo("password", password);
        List<User> users = userDao.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}