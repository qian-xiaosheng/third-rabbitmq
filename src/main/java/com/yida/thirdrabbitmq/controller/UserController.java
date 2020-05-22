package com.yida.thirdrabbitmq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yida.thirdrabbitmq.entity.BaseResponse;
import com.yida.thirdrabbitmq.entity.User;
import com.yida.thirdrabbitmq.entity.UserLog;
import com.yida.thirdrabbitmq.service.UserLogService;
import com.yida.thirdrabbitmq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author 钱老师
 * @since 2020-05-21 16:04:04
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private UserLogService userLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public BaseResponse login(@RequestParam("userName") String userName,
                              @RequestParam("password") String password){
        BaseResponse response=new BaseResponse(true);
        try {
            //TODO：执行登录逻辑
            User user = userService.selectByUserNamePassword(userName,password);
            if (user != null){
                //TODO：异步写用户日志
                try {
                    UserLog userLog = new UserLog(userName,"Login","login",
                            objectMapper.writeValueAsString(user));
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
                    rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));

                    Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog))
                            .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
                    message.getMessageProperties()
                            .setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                                    MessageProperties.CONTENT_TYPE_JSON);
                    rabbitTemplate.convertAndSend(message);
                    log.info("异步写用户日志({})成功", user);
                }catch (Exception e){
                    e.printStackTrace();
                }

                //TODO：权限数据-资源数据-视野数据
                //...
            }else{
                response=new BaseResponse(false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}