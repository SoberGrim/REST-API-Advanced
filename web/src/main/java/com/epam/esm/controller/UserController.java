package com.epam.esm.controller;

import com.epam.esm.attribute.ResponseAttribute;
import com.epam.esm.dto.Order;
import com.epam.esm.dto.Tag;
import com.epam.esm.dto.User;
import com.epam.esm.hateoas.Hateoas;
import com.epam.esm.response.OperationResponse;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.TagService;
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

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService<User> userService;
    private final OrderService<Order> orderService;
    private final TagService<Tag> tagService;
    private final Hateoas<User> userHateoas;
    private final Hateoas<Order> orderHateoas;
    private final Hateoas<Tag> tagHateoas;
    private final Hateoas<OperationResponse> responseHateoas;

    /**
     * Instantiates a new User controller.
     *
     * @param userService     the user service
     * @param orderService    the order service
     * @param tagService      the tag service
     * @param userHateoas     the user hateoas
     * @param orderHateoas    the order hateoas
     * @param tagHateoas      the tag hateoas
     * @param responseHateoas the response hateoas
     */
    @Autowired
    public UserController(UserService<User> userService, OrderService<Order> orderService, TagService<Tag> tagService,
                          Hateoas<User> userHateoas, Hateoas<Order> orderHateoas, Hateoas<Tag> tagHateoas,
                          @Qualifier("orderOperationResponseHateoas") Hateoas<OperationResponse> responseHateoas) {
        this.userService = userService;
        this.orderService = orderService;
        this.tagService = tagService;
        this.userHateoas = userHateoas;
        this.orderHateoas = orderHateoas;
        this.tagHateoas = tagHateoas;
        this.responseHateoas = responseHateoas;
    }

    /**
     * Find user by id user.
     *
     * @param id the id
     * @return the user
     */
    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        User user = userService.findById(id);
        userHateoas.createHateoas(user);
        return user;
    }

    /**
     * Find all users list.
     *
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    @GetMapping
    public List<User> findAllUsers(@RequestParam int page, @RequestParam int elements) {
        List<User> users = userService.findAll(page, elements);
        users.forEach(userHateoas::createHateoas);
        return users;
    }

    /**
     * Create order operation response.
     *
     * @param userId        the user id
     * @param certificateId the certificate id
     * @return the operation response
     */
    @PatchMapping("/{userId}/orders/new/{certificateId}")
    public OperationResponse createOrder(@PathVariable String userId, @PathVariable String certificateId) {
        OperationResponse response = new OperationResponse(OperationResponse.Operation.CREATION,
                ResponseAttribute.ORDER_CREATE_OPERATION, String.valueOf(orderService.createOrder(userId,
                certificateId)));
        responseHateoas.createHateoas(response);
        return response;
    }

    /**
     * Find user orders list.
     *
     * @param userId   the user id
     * @param page     the page
     * @param elements the elements
     * @return the list
     */
    @GetMapping("/{userId}/orders")
    public List<Order> findUserOrders(@PathVariable String userId, @RequestParam int page, @RequestParam int elements) {
        List<Order> orders = orderService.findByUserId(page, elements, userId);
        orders.forEach(orderHateoas::createHateoas);
        return orders;
    }

    /**
     * Find user order order.
     *
     * @param userId  the user id
     * @param orderId the order id
     * @return the order
     */
    @GetMapping("/{userId}/orders/{orderId}")
    public Order findUserOrder(@PathVariable String userId, @PathVariable String orderId) {
        Order order = orderService.findByUserIdAndOrderId(userId, orderId);
        orderHateoas.createHateoas(order);
        return order;
    }

    /**
     * Find most used tag of user with highest cost of all orders tag.
     *
     * @param userId the user id
     * @return the tag
     */
    @GetMapping("/{userId}/orders/tags/popular")
    public Tag findMostUsedTagOfUserWithHighestCostOfAllOrders(@PathVariable String userId) {
        Tag tag = tagService.findMostUsedTagOfUserWithHighestCostOfAllOrders(userId);
        tagHateoas.createHateoas(tag);
        return tag;
    }
}
