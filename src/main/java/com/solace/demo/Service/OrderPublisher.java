package com.solace.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

        @Autowired
        private StreamBridge streamBridge;


        public void publishOrder(String orderId){
            String message = "New Message Created" + orderId;
            streamBridge.send("order-out-0",message);
            System.out.println("✅ Published message: " + message);
        }
}
