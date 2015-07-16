package com.inlog.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inlog.entities.Item;
import com.inlog.entities.SearchKeyValuePair;
import com.inlog.services.IItemService;

@RestController
@RequestMapping("/api/v1")
public class ItemController {
	private final IItemService itemservice;

	@Autowired
	public ItemController(IItemService itemservice) {
		this.itemservice = itemservice;
	}

	@RequestMapping(value = "/addItemDetails", method = RequestMethod.POST, produces = "application/json")
	public Item insertItemDetails(@ModelAttribute("item") Item item) {
		itemservice.saveItemDetails(item);

		return item;
	}

	@RequestMapping(value = "/showItemDetailsByCriteria", method = RequestMethod.GET, produces = "application/json")
	public List<Item> showItemDetailsByCriteria(
			@ModelAttribute("item") Item item) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return itemservice.getItemDetails(item);
	}

	@RequestMapping(value = "/showItemDetails", method = RequestMethod.GET, produces = "application/json")
	public List<Item> showItemDetails() {
		return itemservice.getAllItems();
	}

	/**
	 * Request should be in JSON format [{"key" :"data","value" : "data"}]
	 * @param paramList
	 * @return
	 */
	@RequestMapping(value = "/showItemDetailsByCriteriaMap", method = RequestMethod.POST, produces = "application/json")
	public List<Item> showItemDetailsByCriteriaMap(
			@RequestBody List<SearchKeyValuePair> paramList) {
		return itemservice.getItemDetailsWithMap(paramList);
	}
}
