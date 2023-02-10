package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}/blog")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({"/main","/main/{cno}","/main/{cno}/{pno}"})
	public String main(@PathVariable("id") String id,  
			@PathVariable("cno") Optional<Long> cno,
			@PathVariable("pno") Optional<Long> pno,
			PostVo vo,
			Model model) {
	
		BlogVo blogvo = blogService.findBlog(id);
		model.addAttribute("id",id);
		
		if(pno.isPresent()) {
			Long categoryNo = cno.get();
			Long postNo = pno.get();
			
			model.addAttribute("blogvo",blogvo);
			model.addAttribute("id",id);
			
			List<CategoryVo> list = categoryService.findCategory(id);
			List<PostVo> postList = blogService.findPostNo(categoryNo);
			System.out.println(postList);
			System.out.println("cccc" + categoryNo);
			
			
			
			PostVo post = blogService.Postfindbyno(postNo);
			System.out.println(post);
			model.addAttribute("post",post);
			model.addAttribute("postList", postList);
			model.addAttribute("list",list);

		} else if(cno.isPresent()) {
			List<CategoryVo> list = categoryService.findCategory(id);
			
			model.addAttribute("blogvo",blogvo);
			model.addAttribute("list",list);
			model.addAttribute("id",id);
			Long categoryNo = cno.get();
			
			List<PostVo> postList = blogService.findPostNo(categoryNo);
			model.addAttribute("postList", postList);
			
		} else {
			model.addAttribute("blogvo",blogvo);
			model.addAttribute("id",id);

			List<CategoryVo> list = categoryService.findCategory(id);
			model.addAttribute("list",list);
			
			List<PostVo> postvo = blogService.findPost();
			model.addAttribute("postvo",postvo);
		}
			
		
		return "blog/main";
	}
	
	@RequestMapping(value="/admin-basic", method=RequestMethod.GET)
	public String basic(
			@PathVariable("id") String id, 
			Model model) {
		BlogVo blogvo = blogService.findBlog(id);
		model.addAttribute("blogvo",blogvo);
		model.addAttribute("id",id);
		return "blog/admin-basic";
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	public String basic(BlogVo vo,
			@PathVariable("id") String id,
			@RequestParam("file") MultipartFile file,
			Model model) {

		String profile = fileuploadService.restore(file);
		vo.setId(id);
		
		if(profile != null) {
			vo.setProfile(profile);
		}
		
		blogService.updateBlog(vo);
		servletContext.setAttribute("blogvo", vo);
		return "redirect:"+ id + "/blog/admin-basic";
	}
	
	@RequestMapping(value="/admin-category", method=RequestMethod.GET)
	public String category(
			@PathVariable("id") String id, 
			Model model) {
		List<CategoryVo> list = categoryService.findCategory(id);
		model.addAttribute("list",list);
		model.addAttribute("id",id);
		return "blog/admin-category";
	}
	
	@RequestMapping(value="/admin-category", method=RequestMethod.POST)
	public String category(CategoryVo vo,
			@PathVariable("id") String id, 
			Model model) {
		categoryService.insertUser(vo);
		return "redirect:/"+ id + "/blog/admin-category";
	}
	
	@RequestMapping(value="/admin-category/delete/{no}")
	public String delete(@PathVariable("no") Long no, 
			@PathVariable("id") String id,
			Model model) {
		categoryService.deleteCategory(no);
		return "redirect:/"+ id + "/blog/admin-category";
	}
	
	@RequestMapping(value="/admin-write", method=RequestMethod.GET)
	public String write(PostVo vo,
			@PathVariable("id") String id,
			Model model) {
		List<CategoryVo> list = categoryService.findCategory(id);
		model.addAttribute("list",list);
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin-write", method=RequestMethod.POST)
	public String write(PostVo vo,
			@PathVariable("id") String id) {
		System.out.println("post"+vo);
		blogService.insertPost(vo);
		return "redirect:/"+ id + "/blog/admin-write";
	}
}
