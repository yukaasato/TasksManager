package com.tasks.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tasks.entity.TaskEntity;
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{

}
