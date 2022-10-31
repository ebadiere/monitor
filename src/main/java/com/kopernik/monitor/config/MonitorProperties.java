package com.kopernik.monitor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("monitor")
@Getter
@Setter
public class MonitorProperties {
  private String networkEndpoint;
}
