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

	//task�̕ۑ�
	public void regist(TaskEntity oneTaskEntity) {
		taskRepository.save(oneTaskEntity);
	}

	//task�̑S���擾
	public List<TaskEntity> getTaskList(){
		
		return taskRepository.findAll();
	}
	
	//task��1���擾
	public TaskEntity getTaskEntity(int id) {
		
		TaskEntity taskEntity = taskRepository.findById(id);
		return taskEntity;
	}		
	
}
