package com.yida.thirdrabbitmq.listen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.yida.thirdrabbitmq.entity.UserLog;
import com.yida.thirdrabbitmq.service.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class CommonMqListener {
    private static final Logger log= LoggerFactory.getLogger(CommonMqListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private UserLogService userLogService;

    //@Autowired
    //private MailService mailService;

    /**
     * 监听消费用户的操作日志
     * @param message
     */
    @RabbitListener(queues = "${log.user.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(Message message, Channel channel){
        try {
            String queue = message.getMessageProperties().getConsumerQueue();
            log.info("监听到消费用户日志Queue：{}",queue);
            String exchange = message.getMessageProperties().getReceivedExchange();
            log.info("监听到消费用户日志Exchange：{}",exchange);
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            log.info("监听到消费用户日志Routing-Key：{}",routingKey);
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            log.info("监听到消费用户日志DeliveryTag：{}",deliveryTag);
            UserLog userLog = objectMapper.readValue(message.getBody(), UserLog.class);
            log.info("监听消费用户日志，监听到消息：{} ",userLog);
            //TODO：记录日志入数据表
            userLogService.insert(userLog);
            //手动ACK,发送确认回执信息
            channel.basicAck(deliveryTag, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
