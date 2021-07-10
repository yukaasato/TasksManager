package com.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tasks.request.TaskRequest;




@Controller
public class TaskController {
	
	
	@GetMapping("/task/list") 
	public String displayTaskList(Model model,TaskRequest taskRequest) {
		
		//‚ ‚ê‚ÎDB‚©‚çƒf[ƒ^’T‚µ‚Ä‚­‚é List
		
		model.addAttribute("taskList", "Taskˆê——");
		model.addAttribute("taskRequest", "taskRequest");
		
		return "/list";
	}

	
	
}
