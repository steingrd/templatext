package org.templatext.spring.example.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TodoItem {

	private Long id;
	private boolean finished;
	private String title;
	private String description;
	private int priority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int importance) {
		this.priority = importance;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
