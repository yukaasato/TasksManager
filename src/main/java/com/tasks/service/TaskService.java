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

	//taskの保存
	public void regist(TaskEntity oneTaskEntity) {
		taskRepository.save(oneTaskEntity);
	}

	//taskの全件取得
	public List<TaskEntity> getTaskList(){
		
		return taskRepository.findAll();
	}
	
	/*
	 * //taskの1件取得 public TaskEntity getTaskEntity(int id) {
	 * 
	 * 
	 * return taskRepository.getByID(id); }
	 */	
	public TaskEntity getTaskEntity(int id) {
		
		
		return taskRepository.findByTaskId(id);
	}
	
	//taskの削除
	public void delete(int deleteId) {
	
		taskRepository.deleteById(deleteId);
	
	}

	
}
