package com.autogeneral.agchallenge.rest;

import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.autogeneral.agchallenge.bean.request.TaskBean;
import com.autogeneral.agchallenge.rest.util.TaskUtil;

/**
 * REST controller class for Task services
 * @author rajesh
 */
@RestController
@RequestMapping("/tasks")
@Validated
public class TasksWS {
	
	private final Logger LOGGER = LoggerFactory.getLogger(TasksWS.class);

	/**
	* GET  /validateBrackets?input=<value> : to validate the input string
	* @param input string to validate
	* @return validation result
	*/
	@GetMapping("/validateBrackets")
	@ResponseBody
	public ResponseEntity<TaskBean> validateBrackets(@RequestParam(required = true) 
														@Size(min = 1, max = 50, message = "Must be between 1 and 50 chars long") String input){
		LOGGER.info("REST request to validate brackets=[{}]", input);
		TaskBean tasksBean = new TaskBean();
		tasksBean.setInput(input);
		tasksBean.setIsBalanced(TaskUtil.isValid(input));
		return new ResponseEntity<>(tasksBean, HttpStatus.OK);		
	}
}