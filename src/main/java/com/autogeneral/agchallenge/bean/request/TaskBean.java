package com.autogeneral.agchallenge.bean.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * domain class for Task 
 * @author rajesh
 */
public class TaskBean implements Serializable {

	/**
	 * default value for serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	private String input;
	private boolean isBalanced;
	
	public String getInput() {
		return input;
	}
	public TaskBean setInput(String input) {
		this.input = input;
		return this;
	}
	
	public boolean getIsBalanced() {
		return isBalanced;
	}
	public TaskBean setIsBalanced(boolean isBalanced) {
		this.isBalanced = isBalanced;
		return this;
	}
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}