package com.autogeneral.agchallenge.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Entity class for Item data
 * @author rajesh
 */
@Entity
@Table(name = "to_do_item")
public class ToDoItem implements Serializable {

	/**
	 * default value for serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Must not be Null or Empty")
	@Size(min = 1, max = 50, message = "Must be between 1 and 50 chars lon")
	private String text;
	
	@Column(name = "is_completed")
	private boolean isCompleted;
	
	@Column(name = "created_at")
	private ZonedDateTime createdAt;
	
	public Long getId() {
		return id;
	}

	public ToDoItem setId(Long id) {
		this.id = id;
		return this;
	}

	public String getText() {
		return text;
	}

	public ToDoItem setText(String text) {
		this.text = text;
		return this;
	}

	public boolean getIsCompleted() {
		return isCompleted;
	}

	public ToDoItem setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
		return this;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public ToDoItem setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	@Override
	public int hashCode() {
	    return Objects.hashCode(getId());
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToDoItem entity = (ToDoItem) o;
        if (entity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entity.getId());
    }
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}