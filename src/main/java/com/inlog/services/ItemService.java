package com.inlog.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inlog.commons.CommonUtilities;
import com.inlog.dao.IItemDao;
import com.inlog.dao.repositories.ItemRepository;
import com.inlog.entities.Item;
import com.inlog.entities.SearchKeyValuePair;

@Service
public class ItemService implements IItemService {
	private final ItemRepository itemRepository;
	private final IItemDao itemDao;
	private static final String SORT_CRITERIA = "itemName";

	@Autowired
	public ItemService(ItemRepository itemRepository, IItemDao itemDao) {
		super();
		this.itemRepository = itemRepository;
		this.itemDao = itemDao;
	}

	@Override
	public void saveItemDetails(Item item) {
		itemRepository.save(item);
	}

	@Override
	public List<Item> getItemDetails(Item item) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return itemDao.getItemByCriteria(CommonUtilities.getQuery(item));
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll(new Sort(Sort.Direction.ASC,
				SORT_CRITERIA));
	}

	@Override
	public List<Item> getItemDetailsWithMap(List<SearchKeyValuePair> paramList) {
		return itemDao.getItemByCriteria(CommonUtilities.getQuery(paramList));
	}

}
