package ru.my;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookProvider {

    private final WebClient webClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction reactorLoadBalancerExchangeFilterFunction) {
        this.webClient = WebClient.builder()
                .filter(reactorLoadBalancerExchangeFilterFunction)
                .build();

    }

    public UUID getRandomBookId() {
        BookResponce bookResponce = webClient.get()
                .uri("http://book-service/api/book/random")
                .retrieve()
                .bodyToMono(BookResponce.class)
                .block();
        return bookResponce.getId();

    }

    @Data
    private static class BookResponce {
        private UUID id;
    }

}

