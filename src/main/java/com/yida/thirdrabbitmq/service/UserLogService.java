package com.yida.thirdrabbitmq.service;

import com.yida.thirdrabbitmq.entity.UserLog;

/**
 * (UserLog)表服务接口
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:04
 */
public interface UserLogService {
    UserLog insert(UserLog userLog);
}