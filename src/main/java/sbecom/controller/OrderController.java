// package sbecom.controller;

// import org.aspectj.apache.bcel.generic.RET;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.RequestEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import sbecom.model.dto.OrderRequest;
// import sbecom.model.dto.OrderResponse;
// import sbecom.service.OrderService;

// @RestController
// @RequestMapping("/api")
// public class OrderController {
//     @Autowired
//     private OrderService orderService;

//     @PostMapping("/orders/place")
//     public RequestEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
//         OrderResponse orderResponse = orderService.placeOrder(orderRequest);
//         return new RequestEntity<>(orderResponse, HttpStatus.CREATED);
//     }

//     @GetMapping("/orders")
//     public RequestEntity<OrderResponse> getAllOrders() {
//         OrderResponse orderResponse = orderService.getAllOrders();
//         return new RequestEntity<>(orderResponse, HttpStatus.OK);
//     }
// }
