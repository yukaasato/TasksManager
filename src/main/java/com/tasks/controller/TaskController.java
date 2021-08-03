package com.tasks.controller;

import java.util.List;
import java.util.Optional;

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
	TaskService taskService;

	
	/*
	 *  �g�b�v�y�[�W��\�����郁�\�b�h
	 *  �@�^�X�N�ǉ���ʂ��ł���
	 *  �A�^�X�N�ꗗ��\���ł���
	 */
	@GetMapping("/task/list") 
	public String displayTaskList(Model model, TaskRequest taskRequest) {
		
		//�@DB����f�[�^�T���Ă��� List
		//�@�z��𐶐����āAList�^��TaskEntity���X�g���쐬����B�ϐ����FtaskList
		List<TaskEntity> taskList = taskService.getTaskList();
		
		//�@html��title���󂯓n��
		model.addAttribute("title", "Task�ꗗ");
		
		//�@html��taskList���󂯓n��
		model.addAttribute("taskList",taskList);
		
		//�@html��taskRequest���󂯓n��
		model.addAttribute("taskRequest", taskRequest);
		
		//�@list.html��Ԃ�
		return "/list";
	}
	
	/* �^�X�N���f�[�^�x�[�X�֓o�^���郁�\�b�h */
	@PostMapping("/task/regist")
	public String registOneTask(TaskRequest taskRequest, RedirectAttributes redirectAttributes) {
		
		//�@�G���e�B�e�B����
		TaskEntity oneTaskEntity = new TaskEntity();
		
		//�@�t�H�[������G���e�B�e�B�֋l�ߑւ��� taskRequest��registTaskEntity
		//�@taskName
		oneTaskEntity.setTaskName(taskRequest.getTaskName());
		//�@completeFlag
		oneTaskEntity.setCompleteFlag(false);
		
		//�@�T�[�r�X�N���X�փG���e�B�e�B��n��repositor���g����DB�֓o�^����
		taskService.regist(oneTaskEntity);
	
		//�@�o�^����Msg
		redirectAttributes.addFlashAttribute("completeMsg", "�o�^����");
		
		//�@���_�C���N�g����
		return "redirect:/task/list";
		
	}
	
	/* 
	 * �^�X�N�ڍ׉�ʂ֑J�ڂ���
	 * �@id���g����DB����1���f�[�^���i�荞��
	 * �A�ڍ׉�ʂ�1���̃f�[�^��Entity�Ƃ��ēn��
	 * 
	 */
	@GetMapping("/task/detail")
	public String showTaskDetail(TaskRequest taskRequest, Model model) {
		
				//�G���e�B�e�B�̃C���X�^���X�𐶐�����
				//�p�r�̓f�[�^�x�[�X����擾����1���̃f�[�^���i�[���鎖
				TaskEntity detailEntity =new TaskEntity ();
				
				
				//taskRequest����detailEntity�փZ�b�g����
				//���[�U���������ڍ׃f�[�^���i�荞�ނ���
				detailEntity.setTaskId(taskRequest.getTaskId());
				
				
				
				//
				TaskEntity  oneTask = taskService.getTaskEntity(detailEntity.getTaskId());

				//�^�C�g��
				model.addAttribute("title","My Task");

				//Entity��n��
			    model.addAttribute("oneTask", oneTask);

				//�ڍ׉�ʂ�
				return "/detail";

	}
	
}
