package com.tasks.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.entity.TaskEntity;
import com.tasks.repository.TaskRepository;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	TaskRepository repo;

	public void regist(TaskEntity oneTaskEntity) {
		repo.save(oneTaskEntity);
	}

}
