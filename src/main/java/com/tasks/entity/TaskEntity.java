package com.tasks.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="tasks")
public class TaskEntity {
	
	@Id
	@Column(name = "task_id")		
	@NotNull
	private int task_id;
	
	//　Util.Dateをインポートする
	@Column(name= "task_date")
	private Date task_date;
	
	@Column(name = "task_name")
	private String task_name;
	
	//sql.Time
	@Column(name ="task_time")
	private Time task_time;
	
	@Column(name = "task_place")
	private String task_place;
	
	@Column(name = "complete_flag")
	private Boolean complete_flag;

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public Date getTask_date() {
		return task_date;
	}

	public void setTask_date(Date task_date) {
		this.task_date = task_date;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public Time getTask_time() {
		return task_time;
	}

	public void setTask_time(Time task_time) {
		this.task_time = task_time;
	}

	public String getTask_place() {
		return task_place;
	}

	public void setTask_place(String task_place) {
		this.task_place = task_place;
	}

	public Boolean getComplete_flag() {
		return complete_flag;
	}

	public void setComplete_flag(Boolean complete_flag) {
		this.complete_flag = complete_flag;
	}
	
	
	
}
