package com.epam.esm.controller;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.User;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService<User> userService;
    private final OrderService<Order> orderService;
    private final Hateoas<User> userHateoas;
    private final Hateoas<Order> orderHateoas;
    private final Hateoas<OperationResponse> responseHateoas;

    @Autowired
    public UserController(UserService<User> userService, OrderService<Order> orderService, Hateoas<User> userHateoas,
                          Hateoas<Order> orderHateoas, @Qualifier("orderOperationResponseHateoas")
                                  Hateoas<OperationResponse> responseHateoas) {
        this.userService = userService;
        this.orderService = orderService;
        this.userHateoas = userHateoas;
        this.orderHateoas = orderHateoas;
        this.responseHateoas = responseHateoas;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        User user = userService.findById(id);
        userHateoas.createHateoas(user);
        return user;
    }

    @GetMapping
    public List<User> findAllUsers(@RequestParam int page, @RequestParam int elements) {
        List<User> users = userService.findAll(page, elements);
        users.forEach(userHateoas::createHateoas);
        return users;
    }

    @PatchMapping("/{userId}/orders/new/{certificateId}")
    public OperationResponse createOrder(@PathVariable String userId, @PathVariable String certificateId) {
        OperationResponse response = new OperationResponse(OperationResponse.Operation.CREATION,
                ResponseAttribute.ORDER_CREATE_OPERATION, String.valueOf(orderService.createOrder(userId,
                certificateId)));
        responseHateoas.createHateoas(response);
        return response;
    }

    @GetMapping("/{userId}/orders")
    public List<Order> findUserOrders(@PathVariable String userId, @RequestParam int page, @RequestParam int elements) {
        List<Order> orders = orderService.findByUserId(page, elements, userId);
        orders.forEach(orderHateoas::createHateoas);
        return orders;
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public Order findUserOrder(@PathVariable String userId, @PathVariable String orderId) {
        Order order = orderService.findByUserIdAndOrderId(userId, orderId);
        orderHateoas.createHateoas(order);
        return order;
    }
}
