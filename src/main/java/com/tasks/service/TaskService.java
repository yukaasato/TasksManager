package com.tasks.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.entity.TaskEntity;
import com.tasks.repository.TaskRepository;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;

	public void regist(TaskEntity oneTaskEntity) {
		taskRepository.save(oneTaskEntity);
	}

	public List<TaskEntity> getTaskList(){
		
		return taskRepository.findAll();
	}
}
