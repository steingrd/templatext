package org.javango.spring.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.javango.spring.example.dao.TodoItemDao;
import org.javango.spring.example.model.TodoItem;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class DeleteController extends SimpleFormController {

	private TodoItemDao todoItemDao;

	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		if (id != null) {
			return todoItemDao.getTodoItemById(Long.parseLong(id));
		}
		return null;
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		todoItemDao.delete((TodoItem)command);
		return new ModelAndView(new RedirectView("list.html"));
	}
	
	public TodoItemDao getTodoItemDao() {
		return todoItemDao;
	}

	public void setTodoItemDao(TodoItemDao todoItemDao) {
		this.todoItemDao = todoItemDao;
	}

}
