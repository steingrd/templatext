package org.templatext.spring.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.templatext.spring.example.dao.TodoItemDao;
import org.templatext.spring.example.model.TodoItem;

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
