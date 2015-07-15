package com.inlog.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.inlog.entities.Order;

/**
 * 
 * @author Mayur
 *
 */
public interface IOrderService {
	/**
	 * Insert order details into db
	 * @param order
	 */
	void insertOrderDetails(Order order);

	/**
	 * Get all the orders without any criteria and adds the item response based on
	 * the result set
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	List<Order> getAllOrders() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	/**
	 * Get the orders based on the criteria and adds the items list in the result set
	 * @param order
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	List<Order> getOrderByCriteria(Order order) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
