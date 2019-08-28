package com.autogeneral.agchallenge.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.autogeneral.agchallenge.AgchallengeApplication;
import com.autogeneral.agchallenge.bean.request.ToDoItemBean;
import com.autogeneral.agchallenge.business.ItemService;
import com.autogeneral.agchallenge.entity.ToDoItem;
import com.autogeneral.agchallenge.repository.ItemRepository;
import com.autogeneral.agchallenge.rest.error.RestExceptionTranslator;
import com.autogeneral.agchallenge.rest.util.TestUtil;

/**
 * Test class for ToDoWS
 * @author rajesh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AgchallengeApplication.class)
public class ToDoWSIntTest {
	
	private MockMvc restMockMvc;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	private ToDoItem toDoItem;
	
	private static final String TEXT = "Test Todo Item";
	private static final boolean IS_COMPLETED = true;
	private static final ZonedDateTime CREATED_AT = ZonedDateTime.now();
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TodoWS todoWs = new TodoWS();
        todoWs.setItemService(itemService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(todoWs)
        		.setControllerAdvice(new RestExceptionTranslator())
        		.build();
    }
	
	@Before
    public void initTest() {
		toDoItem = createToDoItem();
    }
	

	private ToDoItem createToDoItem() {
		ToDoItem item = new ToDoItem();
		item.setText(TEXT)
			.setIsCompleted(IS_COMPLETED)
			.setCreatedAt(CREATED_AT);
		return item;
	}
	
	@Test
    @Transactional
    public void getItem() throws Exception {
		// Initialize the data
		toDoItem = itemRepository.saveAndFlush(toDoItem);
		Long itemId = toDoItem.getId();
		
        // get item by item id
		restMockMvc.perform(get("/todo/{id}", itemId))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.id").value(itemId.intValue()))
	        .andExpect(jsonPath("$.text").value(TEXT))
	        .andExpect(jsonPath("$.isCompleted").value(IS_COMPLETED));
    }
	
	@Test
    @Transactional
    public void getItemFail() throws Exception {
		
        // get item by item id
		restMockMvc.perform(get("/todo/{id}", 1000L))
	        .andExpect(status().isNotFound())
	        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
    }
	
	@Test
    @Transactional
    public void modifyItem() throws Exception {
		// Initialize the data
		toDoItem = itemRepository.saveAndFlush(toDoItem);
		Long itemId = toDoItem.getId();
		ToDoItemBean itemBean = new ToDoItemBean();
		itemBean.setIsCompleted(false);
		itemBean.setText("Updated Todo Item");
		
        // patch item by item id
		restMockMvc.perform(patch("/todo/{id}", itemId)
			.contentType(TestUtil.APPLICATION_JSON_UTF8)
			.content(TestUtil.convertObjectToJsonBytes(itemBean)))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.id").value(itemId.intValue()))
	        .andExpect(jsonPath("$.text").value(itemBean.getText()))
	        .andExpect(jsonPath("$.isCompleted").value(itemBean.isCompleted()));
    }
	
	@Test
    public void modifyItemFail() throws Exception {
		// Initialize the data
		ToDoItemBean itemBean = new ToDoItemBean();
		itemBean.setIsCompleted(false);
		itemBean.setText("Updated Todo Item");
        // patch item by item id
		restMockMvc.perform(patch("/todo/{id}", 1000L)
			.contentType(TestUtil.APPLICATION_JSON_UTF8)
			.content(TestUtil.convertObjectToJsonBytes(itemBean)))
	        .andExpect(status().isNotFound())
	        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
    }
	
	@Test
    @Transactional
    public void createItem() throws Exception {
		// Initialize the data
		ToDoItemBean itemBean = new ToDoItemBean();
		itemBean.setIsCompleted(false);
		itemBean.setText("Updated Todo Item");
		
        // patch item by item id
		restMockMvc.perform(post("/todo/")
			.contentType(TestUtil.APPLICATION_JSON_UTF8)
			.content(TestUtil.convertObjectToJsonBytes(itemBean)))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.text").value(itemBean.getText()))
	        .andExpect(jsonPath("$.isCompleted").value(itemBean.isCompleted()));
    }
	

}
