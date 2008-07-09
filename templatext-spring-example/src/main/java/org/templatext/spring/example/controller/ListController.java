package org.templatext.spring.example.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.templatext.spring.example.dao.TodoItemDao;

public class ListController extends AbstractController {

	private TodoItemDao todoItemDao;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Collection<?> items = todoItemDao.getTodoItems();
		return new ModelAndView("list", "todolist", items);
	}

	public void setTodoItemDao(TodoItemDao todoItemDao) {
		this.todoItemDao = todoItemDao;
	}

}
