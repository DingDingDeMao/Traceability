package com.linzhongyoushenjun.Traceability.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：林中有神君
 * @Date：2023/4/15 22:02
 * @Package：com.linzhongyoushenjun.Traceability.config
 */
@Configuration
public class RabbitConfig {

    @Bean("webase_front_queue")
    public Queue queue() {
        //String queue,  队列名
        // boolean durable, 持久化
        // boolean exclusive, 排他的
        // boolean autoDelete, 自动删除
        // Map<String, Object> arguments 属性
        return new Queue("webase_front_queue", true, false, false, null);
    }

    //发布订阅模式
    //交换机名称
    public static final String WEBASE_FRONT_EXCHAGE = "webase_front_exchange";
    //队列名称
    public static final String WEBASE_FRONT_QUEUE_1 = "webase_front_queue_1";


    //定义队列
    @Bean(WEBASE_FRONT_QUEUE_1)
    public Queue WEBASE_FRONT_QUEUE_1() {
        return new Queue(WEBASE_FRONT_QUEUE_1, true, false, false, null);
    }

    //定义交换机
    @Bean(WEBASE_FRONT_EXCHAGE)
    public Exchange WEBASE_FRONT_EXCHAGE() {
        return ExchangeBuilder.fanoutExchange(WEBASE_FRONT_EXCHAGE).durable(true).build();
    }

    //队列绑定交换机
    @Bean
    public Binding WEBASE_FRONT_QUEUE_1_WEBASE_FRONT_EXCHAGE(@Qualifier(WEBASE_FRONT_QUEUE_1) Queue queue,
                                                             @Qualifier(WEBASE_FRONT_EXCHAGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
