package juchoi.study.practice.controller.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

  @Bean
  @ConfigurationProperties(prefix = "beanproperties")
  ConfigProperties createConfigProperties() {
    return new ConfigProperties();
  }
}
