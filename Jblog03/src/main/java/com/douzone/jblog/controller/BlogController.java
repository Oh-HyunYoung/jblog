package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@RequestMapping("/main/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		model.addAttribute("id",id);
		return "blog/main";
	}
	
	@RequestMapping("/admin-basic")
	public String basic() {
		return "blog/admin-basic";
	}
	
	@RequestMapping("/admin-category")
	public String category() {
		return "blog/admin-category";
	}
	
	@RequestMapping("/admin-write")
	public String write() {
		return "blog/admin-write";
	}
}
