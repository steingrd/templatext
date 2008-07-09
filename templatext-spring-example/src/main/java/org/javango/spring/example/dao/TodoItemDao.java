package org.javango.spring.example.dao;

import java.util.Collection;

import org.javango.spring.example.model.TodoItem;

public interface TodoItemDao {

	public abstract void delete(TodoItem item);
	
	public abstract Collection<?> getTodoItems();
	
	public abstract TodoItem getTodoItemById(Long id);
	
	public abstract TodoItem saveOrUpdate(TodoItem item);
	
}
