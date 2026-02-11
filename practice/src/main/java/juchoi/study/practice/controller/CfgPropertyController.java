package juchoi.study.practice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import juchoi.study.practice.controller.model.ConfigProperties;

@RestController
@RequestMapping("/properties")
@ConfigurationProperties(prefix = "myproperties")
public class CfgPropertyController {

  @Value("${valueTest}")
  private String useValue;

  private String useCfgProperties;

  @Autowired
  private ConfigProperties usecCfgPropertiesBean;

  public String getUseCfgProperties() {
    return this.useCfgProperties;
  }

  public void setUseCfgProperties(String ucp) {
    this.useCfgProperties = ucp;
  }

  @GetMapping("/config-bean")
  Optional<ConfigProperties> getCfgProBean() {
    return Optional.of(this.usecCfgPropertiesBean);
  }

  @GetMapping("/value")
  String getValue() {
    return this.useValue;
  }

  @GetMapping("/config")
  String getConfig() {
    return this.useCfgProperties;
  }
}
