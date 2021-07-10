package com.tasks.request;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

@Component
public class TaskRequest {

	@Id
	@Column(name = "task_id")
	@NotNull
	private int task_id;
	
	@Column(name = "task_date")
	private String task_date;
	
	@NotNull
	@Size(min = 1, max = 60, message = "¦ƒ^ƒCƒgƒ‹‚ª–¢“ü—Í‚Å‚·")
	@Column(name = "task_name")
	private String task_name;
	
	@Column(name = "task_time") 
	private String task_time;
	
	@Column(name = "task_place")
	private String task_place;
	
	
	@Column(name = "complete_flag")
	private int complete_flag;


	public int getTask_id() {
		return task_id;
	}


	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}


	public String getTask_date() {
		return task_date;
	}


	public void setTask_date(String task_date) {
		this.task_date = task_date;
	}


	public String getTask_name() {
		return task_name;
	}


	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}


	public String getTask_time() {
		return task_time;
	}


	public void setTask_time(String task_time) {
		this.task_time = task_time;
	}


	public String getTask_place() {
		return task_place;
	}


	public void setTask_place(String task_place) {
		this.task_place = task_place;
	}


	public int getComplete_flag() {
		return complete_flag;
	}


	public void setComplete_flag(int complete_flag) {
		this.complete_flag = complete_flag;
	}
	

}
