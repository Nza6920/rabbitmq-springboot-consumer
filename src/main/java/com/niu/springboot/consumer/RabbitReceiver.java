package com.niu.springboot.consumer;

import com.niu.springboot.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 消费者
 *
 * @author [nza]
 * @version 1.0 [2020/07/09 15:12]
 * @createTime [2020/07/09 15:12]
 */
@Component
public class RabbitReceiver {

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "queue-1",
                    durable = "true"
            ),
            exchange = @Exchange(
                    value = "exchange-1",
                    durable = "true", type = "topic",
                    ignoreDeclarationExceptions = "true"
            ),
            key = {"springboot.*"}
    ))
    public void onMessage(Message message, Channel channel) throws IOException {
        System.err.println("-------------------- 消费 ---------------------------");
        System.out.println("收到消息: ");
        System.out.println(message.getPayload());

        // 获取消息唯一标识
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(
                    value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"
            ),
            exchange = @Exchange(
                    value = "${spring.rabbitmq.listener.order.queue.exchange.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.exchange.durable}",
                    type = "${spring.rabbitmq.listener.order.queue.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.queue.exchange.ignoreDeclarationExceptions}"
            ),
            key = {"${spring.rabbitmq.listener.order.key}"}
    ))
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String, Object> headers,
                               Channel channel) throws IOException {
        System.err.println("-------------------- 消费 ---------------------------");
        System.out.println("收到消息: ");
        System.out.println(order.toString());

        // 获取消息唯一标识
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
