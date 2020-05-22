package com.yida.thirdrabbitmq.service.impl;

import com.yida.thirdrabbitmq.dao.UserLogDao;
import com.yida.thirdrabbitmq.entity.UserLog;
import com.yida.thirdrabbitmq.service.UserLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (UserLog)表服务实现类
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:05
 */
@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {
    @Resource
    private UserLogDao userLogDao;

    @Override
    public UserLog insert(UserLog userLog) {
        this.userLogDao.insertSelective(userLog);
        return userLog;
    }
}