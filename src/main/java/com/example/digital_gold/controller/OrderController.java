package com.example.digital_gold.controller;
import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.service.Order;
import com.example.digital_gold.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.net.URI;

/**
 * @author Sandra Turina
 */

@RestController
public class OrderController {
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody Order requestOrder) {
        logger.info("Uit body via JSON aangemaakt: " + requestOrder);
        Transaction orderTransaction = orderService.processOrder(requestOrder);
        if (orderTransaction != null) {
            return ResponseEntity.created(URI.create("/order")).build();
        } else {
            return ResponseEntity.internalServerError().body("Your order cannot be processed at the moment. " +
                    "Please try again later");
        }
    }
}
