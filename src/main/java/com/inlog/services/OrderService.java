package com.inlog.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inlog.commons.CommonUtilities;
import com.inlog.dao.IOrderDao;
import com.inlog.dao.repositories.OrdeRepository;
import com.inlog.entities.CountDataObject;
import com.inlog.entities.Item;
import com.inlog.entities.Order;

@Service
public class OrderService implements IOrderService {
	private final OrdeRepository orderRespository;
	private final IOrderDao orderDao;
	private final IItemService itemService;
	private static final Logger logger = LoggerFactory
			.getLogger(OrderService.class);

	@Autowired
	public OrderService(OrdeRepository orderRespository, IOrderDao orderDao,
			IItemService itemService) {
		this.orderRespository = orderRespository;
		this.orderDao = orderDao;
		this.itemService = itemService;
	}

	@Override
	public void insertOrderDetails(Order order) {
		orderRespository.save(order);
	}

	@Override
	public List<Order> getAllOrders() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return addItemsToOrder(orderRespository.findAll());
	}

	@Override
	public List<Order> getOrderByCriteria(Order order)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return addItemsToOrder(orderDao.getOrderByCriteria(CommonUtilities
				.getQuery(order)));
	}

	/**
	 * Looks at the itemservice based on the items associated to the offer and
	 * add those item to th result set
	 * 
	 * @param orderList
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private List<Order> addItemsToOrder(List<Order> orderList)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		logger.debug(
				"Setting the item list for each order in the orederlist of size {}",
				orderList.size());
		for (Order order : orderList) {
			List<Item> itemDataList = new ArrayList<Item>();
			List<String> itemIdList = order.getItemList();

			for (String itemId : itemIdList) {
				Item item = new Item();
				item.setId(itemId);

				itemDataList.addAll(itemService.getItemDetails(item));
			}
			order.setItemDataList(itemDataList);

		}

		return orderList;
	}

	@Override
	public List<CountDataObject> getOrderDataForGraph() {
		return orderDao.getOrderDataForGraph();
	}

}
