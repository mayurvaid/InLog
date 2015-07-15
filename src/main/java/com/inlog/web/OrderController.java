package com.inlog.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inlog.entities.Order;
import com.inlog.services.IOrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private final IOrderService orderService;

	@Autowired
	public OrderController(IOrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "/addOrderDetails", method = RequestMethod.POST, produces = "application/json")
	public Order addOrderDetails(@ModelAttribute("order") Order order) {
		orderService.insertOrderDetails(order);
		return order;
	}

	@RequestMapping(value = "/getAllOrder", method = RequestMethod.GET, produces = "application/json")
	public List<Order> getAllOrder() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return orderService.getAllOrders();
	}

	@RequestMapping(value = "/getOrderDetailsByCriteria", method = RequestMethod.GET, produces = "application/json")
	public List<Order> getOrderDetailsByCriteria(
			@ModelAttribute("order") Order order)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return orderService.getOrderByCriteria(order);
	}

}
