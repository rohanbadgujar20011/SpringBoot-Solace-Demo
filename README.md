# 📨 Spring Cloud Stream with Solace Demo

This project demonstrates how to integrate **Spring Cloud Stream** with **Solace PubSub+** for event-driven messaging in a Spring Boot application.

It shows how to:
- Publish messages to a **Solace topic**
- Consume messages from a **Solace queue** bound to that topic
- Use **named durable queues** for reliable message delivery
- Run multiple consumers with **load-balanced or fanout** message distribution

---

## ⚙️ Tech Stack

- **Spring Boot 3.x**
- **Spring Cloud Stream**
- **Solace PubSub+ Binder**
- **Java 17+**

---

## 🧩 Project Structure

src/main/java/com/solace/demo/
│
├── OrderEventProducer.java # Publishes messages to Solace
├── OrderEventConsumer.java # Listens to messages from Solace
└── Application.java # Main Spring Boot entry point

yaml
Copy code

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/spring-solace-demo.git
cd spring-solace-demo
2️⃣ Update Solace Connection Details
Update the following values in src/main/resources/application.properties (or .yml):

properties
Copy code
spring.application.name=spring-solace-demo

# ========================
# Spring Cloud Stream Bindings
# ========================

# Outbound binding (Publisher)
spring.cloud.stream.bindings.order-out-0.destination=order/created

# Inbound binding (Subscriber)
spring.cloud.stream.bindings.order-in-0.destination=order/created
spring.cloud.stream.bindings.order-in-0.group=my-order-queue

# Solace-specific settings
spring.cloud.stream.solace.bindings.order-out-0.producer.delivery-mode=PERSISTENT

# ========================
# Solace Broker Connection
# ========================
solace.java.host=tcps://<your-solace-host>:55443
solace.java.msgVpn=<your-vpn-name>
solace.java.clientUsername=<your-username>
solace.java.clientPassword=<your-password>
3️⃣ Publisher Example (OrderEventProducer.java)
java
Copy code
package com.solace.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void sendOrder(String orderJson) {
        streamBridge.send("order-out-0", orderJson);
        System.out.println("📤 Sent order event: " + orderJson);
    }
}
4️⃣ Consumer Example (OrderEventConsumer.java)
java
Copy code
package com.solace.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class OrderEventConsumer {

    @Bean
    public Consumer<String> orderIn() {
        return message -> {
            System.out.println("📦 Received order event: " + message);
        };
    }
}
5️⃣ Run the Application
Start the Spring Boot app:

bash
Copy code
./mvnw spring-boot:run
You should see:

css
Copy code
📤 Sent order event: {"orderId":"123", "status":"CREATED"}
📦 Received order event: {"orderId":"123", "status":"CREATED"}
🧠 How Solace Works Here
🔹 Topics
Messages are published to a topic (e.g., order/created).

🔹 Queues
Queues persist messages and allow consumers to consume them reliably.

By configuring:

properties
Copy code
spring.cloud.stream.bindings.order-in-0.group=my-order-queue
Spring automatically creates a durable queue (e.g. spring-solace-demo.my-order-queue) that subscribes to the order/created topic.

👥 Multiple Consumers
Scenario	Behavior
Consumers use the same queue name	Each message is consumed by only one instance (load-balanced).
Consumers use different queue names	Each gets its own copy of the message (fan-out).

🧰 Useful Links
Spring Cloud Stream Solace Binder Docs

Solace Cloud Console

Solace PubSub+ Event Broker Overview

🧾 License
This project is licensed under the MIT License.

Author: Your Name
Company: Jio Platforms Ltd
Description: Simple demo of Spring Cloud Stream integration with Solace PubSub+ for reliable, event-driven communication.

yaml
Copy code

---

Would you like me to include a **section explaining how to create and verify the queue** directly in the Solace Cloud Console (with screenshots or CLI commands)? That can make your README more practical for teammates.






