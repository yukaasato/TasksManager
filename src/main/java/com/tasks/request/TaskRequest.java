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
	private int taskId;
	
	@Column(name = "task_date")
	private String taskDate;
	
	@NotNull
	@Size(min = 1, max = 60, message = "Å¶É^ÉCÉgÉãÇ™ñ¢ì¸óÕÇ≈Ç∑")
	@Column(name = "task_name")
	private String taskName;
	
	@Column(name = "task_time") 
	private String taskTime;
	
	@Column(name = "task_place")
	private String taskPlace;
	
	
	@Column(name = "complete_flag")
	private int completeFlag;


	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


	public String getTaskDate() {
		return taskDate;
	}


	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getTaskTime() {
		return taskTime;
	}


	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}


	public String getTaskPlace() {
		return taskPlace;
	}


	public void setTaskPlace(String taskPlace) {
		this.taskPlace = taskPlace;
	}


	public int getCompleteFlag() {
		return completeFlag;
	}


	public void setCompleteFlag(int completeFlag) {
		this.completeFlag = completeFlag;
	}

	

	
}
