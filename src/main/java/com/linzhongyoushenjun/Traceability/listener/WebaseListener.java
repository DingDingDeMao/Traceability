package com.linzhongyoushenjun.Traceability.listener;
import com.linzhongyoushenjun.Traceability.config.RabbitConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author：林中有神君
 * @Date：2023/4/15 22:13
 * @Package：com.linzhongyoushenjun.Traceability.listener
 */
@Component
public class WebaseListener {
    @RabbitListener(queues = RabbitConfig.WEBASE_FRONT_QUEUE_1)
    public void receiveMsg(Message message){
        //拿到事件订阅之后的业务逻辑
        byte[] body = message.getBody();
        System.out.println(new String(body));
        MessageProperties messageProperties = message.getMessageProperties(); //参数
        System.out.println(messageProperties.getMessageId());
        System.out.println(messageProperties.getDeliveryTag());
        System.out.println(messageProperties.getReceivedRoutingKey());
        System.out.println(messageProperties.getConsumerTag());
    }
}
