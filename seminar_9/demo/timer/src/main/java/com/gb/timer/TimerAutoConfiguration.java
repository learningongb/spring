package com.gb.timer;

import com.gb.timer.aspect.TimerAspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimerProperties.class)
public class TimerAutoConfiguration {

    @Bean
    public TimerAspect timerAspect(TimerProperties properties) {
        return new TimerAspect(properties);
    }
}
