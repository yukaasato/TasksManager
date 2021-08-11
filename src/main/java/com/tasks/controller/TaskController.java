package com.tasks.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

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
		TaskEntity detailEntity = new TaskEntity ();

		//taskRequest����detailEntity�փZ�b�g����
		//���[�U���������ڍ׃f�[�^���i�荞�ނ���
		detailEntity.setTaskId(taskRequest.getTaskId());


		//�^�C�g��
		model.addAttribute("myTasks","My Tasks");

		//Entity��n��
		model.addAttribute("oneTask", detailEntity);

		//�ڍ׉�ʂ�
		return "/detail";

	}
	
	/* 
	 * �^�X�N���X�V����
	 * �@�G���e�B�e�B�֒l���Z�b�g���X�V����
	 * �Alist.html���ĕ\������
	 * 
	 */
	@PostMapping("/task/update")
	public String taskUpdate(TaskRequest taskRequest, Model model) {
		
		//update�p�̃C���X�^���X�쐬
		TaskEntity updateEntity = new TaskEntity();

		//Request����G���e�B�e�B�֒l���Z�b�g����
		updateEntity.setTaskId(taskRequest.getTaskId());;
		updateEntity.setTaskName(taskRequest.getTaskName());
		updateEntity.setTaskPlace(taskRequest.getTaskPlace());		
		updateEntity.setCompleteFlag(taskRequest.isCompleteFlag());	
		
		if(taskRequest.getTaskDate() != null && taskRequest.getTaskDate().length() == 10 ){
			updateEntity.setTaskDate(Date.valueOf(taskRequest.getTaskDate()));
		}
		
		//String��hh:mm:ss�ϊ��@�^��Time�^�֕ϊ� 
		if(taskRequest.getTaskTime().length() == 5 ) {
			updateEntity.setTaskTime(Time.valueOf(taskRequest.getTaskTime() + ":00"));
		}else if(taskRequest.getTaskTime() != null && taskRequest.getTaskTime().length() == 8 ) {
			updateEntity.setTaskTime(Time.valueOf(taskRequest.getTaskTime()));	
		}

		//�X�V
		taskService.regist(updateEntity);

		//taskList�ĕ\��
		List<TaskEntity> taskList = taskService.getTaskList();
		model.addAttribute("title", "Task�ꗗ");
		model.addAttribute("taskList",taskList);
		model.addAttribute("taskRequest", taskRequest);

		return "redirect:/task/list";
	}
}
