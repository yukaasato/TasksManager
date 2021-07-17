package com.tasks.controller;

import java.util.ArrayList;
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
	 *  トップページを表示するメソッド
	 *  ①タスク追加画面ができる
	 *  ②タスク一覧を表示できる
	 */
	@GetMapping("/task/list") 
	public String displayTaskList(Model model, TaskRequest taskRequest) {
		
		//　DBからデータ探してくる List
		//　配列を生成して、List型のTaskEntityリストを作成する。変数名：taskList
		List<TaskEntity> taskList = new ArrayList<>();//ジェネリクス　型を自動変換してくれる、ここでいうとtaskEntity　
		
		//　taskServiceでfindAll();でListを
		taskList = taskService.getTaskList();
		
		//　htmlへtitleを受け渡す
		model.addAttribute("title", "Task一覧");
		
		//　htmlへtaskListを受け渡す
		model.addAttribute("taskList",taskList);
		
		//　htmlへtaskRequestを受け渡す
		model.addAttribute("taskRequest", taskRequest);
		
		//　list.htmlを返す
		return "/list";
	}
	
	/* タスクをデータベースへ登録するメソッド */
	@PostMapping("/task/regist")
	public String registOneTask(TaskRequest taskRequest, RedirectAttributes redirectAttributes) {
		
		//　エンティティ生成
		TaskEntity oneTaskEntity = new TaskEntity();
		
		//　フォームからエンティティへ詰め替える taskRequest→registTaskEntity
		//　taskName
		oneTaskEntity.setTaskName(taskRequest.getTaskName());
		//　completeFlag
		oneTaskEntity.setCompleteFlag(false);
		
		//　サービスクラスへエンティティを渡しrepositorを使ってDBへ登録する
		taskService.regist(oneTaskEntity);
	
		//　登録完了Msg
		redirectAttributes.addFlashAttribute("completeMsg", "登録完了");
		
		//　リダイレクトする
		return "redirect:/task/list";
		
	}
	
	
}
