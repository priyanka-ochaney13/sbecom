package sbecom.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import sbecom.model.OrderItem;
import sbecom.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sbecom.model.dto.OrderResponse;
import sbecom.model.dto.OrderItemRequest;
import sbecom.model.dto.OrderItemResponse;
import sbecom.model.dto.OrderRequest;
import sbecom.repo.OrderRepo;
import sbecom.repo.ProductRepo;
import sbecom.model.Order;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest itemRequest: orderRequest.items()) {
            Product product = productRepo.findById(itemRequest.productId())
                                        .orElseThrow(() -> new RuntimeException("Product not found"));
            
            if(product.getStockQuantity() < itemRequest.quantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            } else {
                product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());

            }

            productRepo.save(product);

            OrderItem orderItem = OrderItem.builder()
                                           .product(product)
                                           .quantity(itemRequest.quantity())
                                           .totalPrice(product.getPrice() * itemRequest.quantity())
                                           .order(order)
                                           .build();
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for(OrderItem orderItem: order.getItems()) {
            OrderItemResponse itemResponse = new OrderItemResponse(
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getTotalPrice()
            );
            itemResponses.add(itemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
            savedOrder.getOrderId(),
            savedOrder.getCustomerName(),
            savedOrder.getEmail(),
            savedOrder.getStatus(),
            savedOrder.getOrderDate(),
            itemResponses
        );
        return orderResponse;
    }

    @Transactional
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepo.findAll();

        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Order order: orders) {

            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for (OrderItem orderItem: order.getItems()) {
                OrderItemResponse itemResponse = new OrderItemResponse(
                    orderItem.getProduct().getName(),
                    orderItem.getQuantity(),
                    orderItem.getTotalPrice()
                );
                itemResponses.add(itemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                itemResponses
            );

        orderResponses.add(orderResponse);
            
        }

        return orderResponses;

    }
}
