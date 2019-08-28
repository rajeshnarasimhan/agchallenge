package com.autogeneral.agchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autogeneral.agchallenge.entity.ToDoItem;

/**
 * Repository interface for Item Entity
 * @author rajesh
 */
@Repository
public interface ItemRepository extends JpaRepository<ToDoItem, Long> {
}
