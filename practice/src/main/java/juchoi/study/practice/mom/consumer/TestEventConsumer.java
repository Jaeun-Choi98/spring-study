package juchoi.study.practice.mom.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TestEventConsumer {

  @Bean
  public Consumer<Message<String>> customTest() {
    return message -> {
      String str = message.getPayload();

      log.info("메시지 수신: {}", str);
      log.info("헤더: {}", message.getHeaders());
    };
  }
}
