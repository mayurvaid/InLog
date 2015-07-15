package com.inlog.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.inlog.entities.Item;
import com.inlog.entities.SearchKeyValuePair;

/**
 * 
 * @author Mayur
 *
 */
public interface IItemService {
	/*
	 * Saves the item details into db 
	 */
	void saveItemDetails(Item item);
	
	/**
	 * 
	 * @param item
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	List<Item> getItemDetails(Item item) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/*
	 * 
	 */
	List<Item> getAllItems();
	
	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Item> getItemDetailsWithMap(List<SearchKeyValuePair> paramList);
}
