package com.solace.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class OrderEventConsumer {


    @Bean
    public Consumer<String> orderIn(){
        return message->{
            System.out.println("📦 Received order event: " + message);

        };
    }
}
