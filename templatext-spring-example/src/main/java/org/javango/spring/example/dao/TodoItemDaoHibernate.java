package org.javango.spring.example.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.javango.spring.example.model.TodoItem;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TodoItemDaoHibernate extends HibernateDaoSupport implements TodoItemDao {
	
	private static Logger LOG = Logger.getLogger(TodoItemDaoHibernate.class); 
	
	public void delete(TodoItem item) {
		LOG.debug("deleting object " + item);
		getHibernateTemplate().delete(item);
	}
	
	public Collection<?> getTodoItems() {
		return getHibernateTemplate().loadAll(TodoItem.class);
	}

	public TodoItem getTodoItemById(Long id) {
		TodoItem item = (TodoItem)getHibernateTemplate().load(TodoItem.class, id);
		LOG.debug("request for object with id " + id + " returned " + item);
		return item;
	}

	public TodoItem saveOrUpdate(TodoItem item) {
		LOG.debug("saving/updating object " + item);
		getHibernateTemplate().saveOrUpdate(item);
		return item;
	}

}
