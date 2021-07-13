package com.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tasks.entity.TaskEntity;
import com.tasks.request.TaskRequest;
import com.tasks.service.TaskService;




@Controller
public class TaskController {
	
	@Autowired
	TaskService srv;

	
	/*
	 *  �g�b�v�y�[�W��\�����郁�\�b�h
	 *  �@�^�X�N�ǉ���ʂ��ł���
	 *  �A�^�X�N�ꗗ��\���ł���
	 */
	@GetMapping("/task/list") 
	public String displayTaskList(Model model,TaskRequest taskRequest) {
		
		//�@DB����f�[�^�T���Ă��� List
		
		
		//�@Title�ɒǉ�
		model.addAttribute("taskList", "Task�ꗗ");
		
		//�@taskRequest�̃C���X�^���X��thymleaf���֎󂯓n��
		model.addAttribute("taskRequest", taskRequest);
		
		//�@list.html��Ԃ�
		return "/list";
	}
	
	/* �^�X�N���f�[�^�x�[�X�֓o�^���郁�\�b�h */
	@PostMapping("/task/regist")
	public String registOneTask(TaskRequest taskRequest,RedirectAttributes redirectAttributes) {
		
		//�@�G���e�B�e�B����
		TaskEntity oneTaskEntity = new TaskEntity();
		
		//�@�t�H�[������G���e�B�e�B�֋l�ߑւ��� taskRequest��registTaskEntity
		//�@task_name
		oneTaskEntity.setTask_name(taskRequest.getTask_name());
		//�@complete_flag
		oneTaskEntity.setComplete_flag(false);
		
		//�@�T�[�r�X�N���X�փG���e�B�e�B��n��repositor���g����DB�֓o�^����
		srv.regist(oneTaskEntity);
	
		//�@�o�^����Msg
		redirectAttributes.addFlashAttribute("completeMsg", "�o�^����");
		
		//�@���_�C���N�g����
		return "redirect:/task/list";
		
	}
	
	
}
