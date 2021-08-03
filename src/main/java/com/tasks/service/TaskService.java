package com.tasks.service;

import java.util.List;
import java.util.Optional;

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

	//task‚Ì•Û‘¶
	public void regist(TaskEntity oneTaskEntity) {
		taskRepository.save(oneTaskEntity);
	}

	//task‚Ì‘SŒæ“¾
	public List<TaskEntity> getTaskList(){
		
		return taskRepository.findAll();
	}
	
	//task‚Ì1Œæ“¾
	public TaskEntity getTaskEntity(int id) {
		
		TaskEntity taskEntity = taskRepository.findById(id);
		return taskEntity;
	}		
	
}
