package com.autogeneral.agchallenge.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.autogeneral.agchallenge.bean.request.ToDoItemBean;
import com.autogeneral.agchallenge.entity.ToDoItem;

/**
 * Service interface for item entity
 * @author rajesh
 */
public interface ItemService {
	
	/**
	 * find all Item records page by page
	 * @param pagination info
	 * @return page of Item records
	 */
	Page<ToDoItem> findAll(Pageable pageable);
	
	/**
	 * get item by itemId
	 * @param itemId
	 * @return Optional containing Item or Empty optional if Item not found for the id
	 */
	Optional<ToDoItem> getItem(Long itemId);
	
	/**
	 * create item
	 * @param item info
	 * @return created item
	 */
	ToDoItem createItem(ToDoItemBean itemBean);
	
	/**
	 * modify item by itemId
	 * @param itemId
	 * @param item info
	 * @return modified item or Empty optional if Item not found for the id
	 */
	Optional<ToDoItem> modifyItem(Long itemId, ToDoItemBean itemBean);
	
	
}
