package org.templatext.spring.example.dao;

import java.util.Collection;

import org.templatext.spring.example.model.TodoItem;

public interface TodoItemDao {

	public abstract void delete(TodoItem item);
	
	public abstract Collection<?> getTodoItems();
	
	public abstract TodoItem getTodoItemById(Long id);
	
	public abstract TodoItem saveOrUpdate(TodoItem item);
	
}
