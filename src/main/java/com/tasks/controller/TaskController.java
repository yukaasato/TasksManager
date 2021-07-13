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
	 *  トップページを表示するメソッド
	 *  ①タスク追加画面ができる
	 *  ②タスク一覧を表示できる
	 */
	@GetMapping("/task/list") 
	public String displayTaskList(Model model,TaskRequest taskRequest) {
		
		//　DBからデータ探してくる List
		
		
		//　Titleに追加
		model.addAttribute("taskList", "Task一覧");
		
		//　taskRequestのインスタンスをthymleaf側へ受け渡す
		model.addAttribute("taskRequest", taskRequest);
		
		//　list.htmlを返す
		return "/list";
	}
	
	/* タスクをデータベースへ登録するメソッド */
	@PostMapping("/task/regist")
	public String registOneTask(TaskRequest taskRequest,RedirectAttributes redirectAttributes) {
		
		//　エンティティ生成
		TaskEntity oneTaskEntity = new TaskEntity();
		
		//　フォームからエンティティへ詰め替える taskRequest→registTaskEntity
		//　task_name
		oneTaskEntity.setTask_name(taskRequest.getTask_name());
		//　complete_flag
		oneTaskEntity.setComplete_flag(false);
		
		//　サービスクラスへエンティティを渡しrepositorを使ってDBへ登録する
		srv.regist(oneTaskEntity);
	
		//　登録完了Msg
		redirectAttributes.addFlashAttribute("completeMsg", "登録完了");
		
		//　リダイレクトする
		return "redirect:/task/list";
		
	}
	
	
}
