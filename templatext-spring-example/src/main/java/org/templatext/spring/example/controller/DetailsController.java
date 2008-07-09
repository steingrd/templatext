package org.templatext.spring.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.templatext.spring.example.dao.TodoItemDao;
import org.templatext.spring.example.model.TodoItem;

public class DetailsController extends SimpleFormController {

	private TodoItemDao todoItemDao;
	
	public DetailsController() {
		setCommandClass(TodoItem.class);
		setCommandName("item");
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			return todoItemDao.getTodoItemById(Long.parseLong(id));
		}
		return new TodoItem();
	}
	
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		TodoItem item = (TodoItem)command;
		todoItemDao.saveOrUpdate(item);
		return new ModelAndView(new RedirectView("details.html?id=" + item.getId()));
	}
	
	public TodoItemDao getTodoItemDao() {
		return todoItemDao;
	}

	public void setTodoItemDao(TodoItemDao todoItemDao) {
		this.todoItemDao = todoItemDao;
	}

}
