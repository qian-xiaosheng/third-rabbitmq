package com.yida.thirdrabbitmq.dao;

import com.yida.thirdrabbitmq.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:04
 */
public interface UserDao extends Mapper<User> {
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);
}