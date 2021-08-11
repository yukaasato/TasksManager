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
	 *  トップページを表示するメソッド
	 *  ①タスク追加画面ができる
	 *  ②タスク一覧を表示できる
	 */
	@GetMapping("/task/list") 
	public String displayTaskList(Model model, TaskRequest taskRequest) {

		//　DBからデータ探してくる List
		//　配列を生成して、List型のTaskEntityリストを作成する。変数名：taskList
		List<TaskEntity> taskList = taskService.getTaskList();

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

	/* 
	 * タスク詳細画面へ遷移する
	 * ①idを使ってDBから1件データを絞り込む
	 * ②詳細画面へ1件のデータをEntityとして渡す
	 * 
	 */
	@GetMapping("/task/detail")
	public String showTaskDetail(TaskRequest taskRequest, Model model) {

		//エンティティのインスタンスを生成する
		//用途はデータベースから取得する1件のデータを格納する事
		TaskEntity detailEntity = new TaskEntity ();

		//taskRequestからdetailEntityへセットする
		//ユーザが見たい詳細データを絞り込むため
		detailEntity.setTaskId(taskRequest.getTaskId());


		//タイトル
		model.addAttribute("myTasks","My Tasks");

		//Entityを渡す
		model.addAttribute("oneTask", detailEntity);

		//詳細画面へ
		return "/detail";

	}
	
	/* 
	 * タスクを更新する
	 * ①エンティティへ値をセットし更新する
	 * ②list.htmlを再表示する
	 * 
	 */
	@PostMapping("/task/update")
	public String taskUpdate(TaskRequest taskRequest, Model model) {
		
		//update用のインスタンス作成
		TaskEntity updateEntity = new TaskEntity();

		//Requestからエンティティへ値をセットする
		updateEntity.setTaskId(taskRequest.getTaskId());;
		updateEntity.setTaskName(taskRequest.getTaskName());
		updateEntity.setTaskPlace(taskRequest.getTaskPlace());		
		updateEntity.setCompleteFlag(taskRequest.isCompleteFlag());	
		
		if(taskRequest.getTaskDate() != null && taskRequest.getTaskDate().length() == 10 ){
			updateEntity.setTaskDate(Date.valueOf(taskRequest.getTaskDate()));
		}
		
		//Stringをhh:mm:ss変換　型をTime型へ変換 
		if(taskRequest.getTaskTime().length() == 5 ) {
			updateEntity.setTaskTime(Time.valueOf(taskRequest.getTaskTime() + ":00"));
		}else if(taskRequest.getTaskTime() != null && taskRequest.getTaskTime().length() == 8 ) {
			updateEntity.setTaskTime(Time.valueOf(taskRequest.getTaskTime()));	
		}

		//更新
		taskService.regist(updateEntity);

		//taskList再表示
		List<TaskEntity> taskList = taskService.getTaskList();
		model.addAttribute("title", "Task一覧");
		model.addAttribute("taskList",taskList);
		model.addAttribute("taskRequest", taskRequest);

		return "redirect:/task/list";
	}
}
