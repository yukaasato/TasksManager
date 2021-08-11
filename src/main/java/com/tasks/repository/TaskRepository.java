package com.tasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasks.entity.TaskEntity;
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{

	@Query(value ="SELECT "
				+ "DISTINCT taskEntity "
				+ "FROM "
				+ "TaskEntity taskEntity "
				+ "WHERE "
				+ "taskEntity.taskId = :taskIdgit ")					
	public TaskEntity findByTaskId(@Param("taskId") int taskId);
	
}
