package com.autogeneral.agchallenge.business.impl;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autogeneral.agchallenge.bean.request.ToDoItemBean;
import com.autogeneral.agchallenge.business.ItemService;
import com.autogeneral.agchallenge.entity.ToDoItem;
import com.autogeneral.agchallenge.repository.ItemRepository;


/**
 * Service class implementation for Item Entity
 * @author rajesh
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	private ItemRepository itemRepository;
	
	@Autowired
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	/**
	 * find all Item records page by page
	 * @param pagination info
	 * @return page of Item records
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ToDoItem> findAll(Pageable pageable) {
		LOGGER.info("Request to find all items");
		return itemRepository.findAll(pageable);
	}

	/**
	 * get item by itemId
	 * @param itemId
	 * @return Optional containing Item or Empty optional if Item not found for the id
	 */
	@Override
	@Transactional
	public Optional<ToDoItem> getItem(Long itemId) {
		LOGGER.info("Request to get Item by Id==[{}]", itemId);
		return itemRepository.findById(itemId);
	}

	/**
	 * create item
	 * @param item info
	 * @return created item
	 */
	@Override
	public ToDoItem createItem(ToDoItemBean itemBean) {
		LOGGER.info("Request to create Item. Item==[{}]", itemBean);
		ToDoItem toDoItem = new ToDoItem();
		toDoItem.setIsCompleted(itemBean.isCompleted())
			.setText(itemBean.getText())
			.setCreatedAt(ZonedDateTime.now());
		return itemRepository.saveAndFlush(toDoItem);
	}

	/**
	 * modify item by itemId
	 * @param itemId
	 * @param item info
	 * @return modified item or Empty optional if Item not found for the id
	 */
	@Override
	public Optional<ToDoItem> modifyItem(Long itemId, ToDoItemBean itemBean) {
		LOGGER.info("Request to update Item by Id==[{}]. Item==[{}]", itemId, itemBean);
		Optional<ToDoItem> itemOpt = getItem(itemId);
		if(itemOpt.isPresent()) {
			ToDoItem toDoItem = itemOpt.get();
			toDoItem.setText(itemBean.getText())
				.setIsCompleted(itemBean.isCompleted());
			itemOpt = Optional.of(itemRepository.saveAndFlush(toDoItem));
		}
		return itemOpt;
	}
}