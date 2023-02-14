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
	
	// blog main 화면
	@RequestMapping({"/main","/main/{cno}","/main/{cno}/{pno}"})
	public String main(@PathVariable("id") String id,  
			@PathVariable("cno") Optional<Long> cno,
			@PathVariable("pno") Optional<Long> pno,
			PostVo vo,
			Model model) {
	
		BlogVo blogvo = blogService.findBlog(id);
		model.addAttribute("id",id);
		
		// main/{cno}/{pno}
		if(pno.isPresent()) {
			Long categoryNo = cno.get();
			Long postNo = pno.get();
			
			model.addAttribute("blogvo",blogvo);
			model.addAttribute("id",id);
			
			List<CategoryVo> list = categoryService.findCategory(id);
			List<PostVo> postList = blogService.findPostNo(categoryNo);
			
			PostVo post = blogService.Postfindbyno(postNo);
			model.addAttribute("post",post);
			model.addAttribute("postList", postList);
			model.addAttribute("list",list);
		
		// main/{cno}
		} else if(cno.isPresent()) {
			List<CategoryVo> list = categoryService.findCategory(id);
			
			model.addAttribute("blogvo",blogvo);
			model.addAttribute("list",list);
			model.addAttribute("id",id);
			Long categoryNo = cno.get();
			
			List<PostVo> postList = blogService.findPostNo(categoryNo);
			model.addAttribute("postList", postList);
		
		// main
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
	
	// basic의 기존 데이터 불러오기
	@RequestMapping(value="/admin-basic", method=RequestMethod.GET)
	public String basic(
			@PathVariable("id") String id, 
			Model model) {
		BlogVo blogvo = blogService.findBlog(id);
		model.addAttribute("blogvo",blogvo);
		model.addAttribute("id",id);
		return "blog/admin-basic";
	}
	
	// basic 제목, 파일 업로드
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
		return "redirect:/"+ id + "/blog/admin-basic";
	}
	
	// 카테고리 리스트 불러오기
	@RequestMapping(value="/admin-category", method=RequestMethod.GET)
	public String category(
			@PathVariable("id") String id, 
			Model model) {
		// blog title을 위해 넣어줌
		BlogVo blogvo = blogService.findBlog(id);
		model.addAttribute("blogvo",blogvo);
		
		List<CategoryVo> list = categoryService.countPostNo(id);
		model.addAttribute("list",list);
		model.addAttribute("id",id);
		return "blog/admin-category";
	}
	
	// 새로운 카테고리 추가
	@RequestMapping(value="/admin-category", method=RequestMethod.POST)
	public String category(CategoryVo vo,
			@PathVariable("id") String id, 
			Model model) {
		categoryService.insertUser(vo);
		return "redirect:/"+ id + "/blog/admin-category";
	}
	
	// 카테고리 삭제
	@RequestMapping(value="/admin-category/delete/{no}")
	public String delete(@PathVariable("no") Long no, 
			@PathVariable("id") String id,
			Model model) {
		categoryService.deleteCategory(no);
		return "redirect:/"+ id + "/blog/admin-category";
	}
	
	// 글작성의 카테고리 리스트 불러오기
	@RequestMapping(value="/admin-write", method=RequestMethod.GET)
	public String write(PostVo vo,
			@PathVariable("id") String id,
			Model model) {
		// blog title을 위해 넣어줌
		BlogVo blogvo = blogService.findBlog(id);
		model.addAttribute("blogvo",blogvo);
		
		List<CategoryVo> list = categoryService.findCategory(id);
		model.addAttribute("list",list);
		return "blog/admin-write";
	}
	
	// 제목 및 내용 삽입
	@RequestMapping(value="/admin-write", method=RequestMethod.POST)
	public String write(PostVo vo,
			@PathVariable("id") String id) {
		blogService.insertPost(vo);
		return "redirect:/"+ id + "/blog/admin-write";
	}
}
