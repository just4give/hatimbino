package com.techfocus.todo.model;

import org.springframework.data.annotation.Id;

public class Todo {

	@Id
	private String id;
	
	private String title;
	
	private Boolean completed;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
    public String toString() {
        return String.format(
                "Todo[id=%s, title='%s', description='%s']",
                id, title, completed);
    }
	
	
}
