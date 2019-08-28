package com.autogeneral.agchallenge.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.autogeneral.agchallenge.bean.request.ToDoItemBean;
import com.autogeneral.agchallenge.business.ItemService;
import com.autogeneral.agchallenge.entity.ToDoItem;
import com.autogeneral.agchallenge.rest.error.ItemNotFoundException;
import com.autogeneral.agchallenge.rest.util.PaginationUtil;

/**
 * REST controller class for Todo services
 * @author rajesh
 */
@RestController
@RequestMapping("/todo")
public class TodoWS {
	
	private final Logger LOGGER = LoggerFactory.getLogger(TodoWS.class);
		
	private ItemService itemService;

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	/**
	* GET  /items : get all the Items page by page.
	* @param pageable the pagination information
	* @return the ResponseEntity with status 200 (OK) and the list of Item in body
	*/
	@GetMapping("/items")
	public ResponseEntity<List<ToDoItem>> getAll(Pageable pageable, HttpServletRequest request) {
		LOGGER.info("REST request to get a page of Item");
		Page<ToDoItem> page = itemService.findAll(pageable);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(request.getRequestURI());
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, uriBuilder.toUriString());
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	* GET  /{id} : restore item by item Id.
	* @param item Id
	* @return the ResponseEntity with status 200 (OK) and updated Item in body
	* if Item not available for the id, then ResponseEntity with status 404 (NOT_FOUND)
	*/
	@GetMapping("/{id}")
	public ResponseEntity<ToDoItem> getItem(@PathVariable Long id) {
		LOGGER.info("REST request to get Item by ItemId=[{}]", id);
		Optional<ToDoItem> item = itemService.getItem(id);
		if(!item.isPresent()) {
			throw new ItemNotFoundException(id);
		}
		return new ResponseEntity<>(item.get(), HttpStatus.OK);
	}
	
	/**
	* POST  / : item with the info.
	* @param ToDoItem
	* @return the ResponseEntity with status 200 (OK) and updated Item in body
	* if Item not available for the id, then ResponseEntity with status 404 (NOT_FOUND)
	*/
	@PostMapping("/")
	public ResponseEntity<ToDoItem> createItem(@Valid @RequestBody ToDoItemBean itemBean) {
		LOGGER.info("REST request to create Item with text=[{}]", itemBean.getText());
		ToDoItem  toDoItem= itemService.createItem(itemBean);		
		return new ResponseEntity<>(toDoItem, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ToDoItem> modifyItem(@PathVariable Long id, @Valid @RequestBody ToDoItemBean itemBean) {
		LOGGER.info("REST request to modify Item by ItemId=[{}]. Item==[{}]", id, itemBean);
		Optional<ToDoItem> item = itemService.modifyItem(id, itemBean);
		if(!item.isPresent()) {
			throw new ItemNotFoundException(id);
		}
		return new ResponseEntity<>(item.get(), HttpStatus.OK);
	}	
}