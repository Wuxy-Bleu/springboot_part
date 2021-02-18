package icu.bleuweb.amqp;

import icu.bleuweb.message.FirstMessageDemo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class DemoProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo01Message 消息
        FirstMessageDemo message = new FirstMessageDemo();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(FirstMessageDemo.EXCHANGE, FirstMessageDemo.ROUTING_KEY, message);
    }

    public void syncSendDefault(Integer id) {
        // 创建 Demo01Message 消息
        FirstMessageDemo message = new FirstMessageDemo();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(FirstMessageDemo.QUEUE, message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            // 发送消息
            this.syncSend(id);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }
}

