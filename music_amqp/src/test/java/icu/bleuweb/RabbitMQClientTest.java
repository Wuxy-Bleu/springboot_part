package icu.bleuweb;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest(classes = RabbitMQClientTest.class)
public class RabbitMQClientTest {

    private final static String QUEUE_NAME = "hello";

    @Test
    void test01() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare("exchangeTest", "direct", true);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            while (true) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                Thread.sleep(500);
            }
        }
    }

}
