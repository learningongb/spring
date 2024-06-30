package com.gb.timer;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("timer")
public class TimerProperties {
    private Level logLevel = Level.INFO;
}
