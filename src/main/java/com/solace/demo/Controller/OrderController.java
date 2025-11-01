package com.solace.demo.Controller;

import com.solace.demo.Service.OrderPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderPublisher publisher;

    @PostMapping("/{id}")
    public String createOrder(@PathVariable String id) {
        publisher.publishOrder(id);
        return "Order created and event published!";
    }
}