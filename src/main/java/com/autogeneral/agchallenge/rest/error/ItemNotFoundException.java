package com.autogeneral.agchallenge.rest.error;

import org.zalando.problem.AbstractThrowableProblem;

/**
 * Custom Exception for Item not fount
 * @author rajesh
 */
public class ItemNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    public ItemNotFoundException(Long id) {
    	super();
    	this.id = id;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}
}
