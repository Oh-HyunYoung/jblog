package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo findBlog(String id) {
		return blogRepository.findblog(id);
	}
	
	public void updateBlog(BlogVo vo) {
		blogRepository.updateblog(vo);
	}
	
	public void insertPost(PostVo vo) {
		blogRepository.insertPost(vo);
	}
	
	public List<PostVo> findPost() {
		return blogRepository.findPost();
	}

	public List<PostVo> findPostNo(Long categoryNo) {
		return blogRepository.findPostNo(categoryNo);
	}
	
	
	public PostVo Postfindbyno(Long postNo) {
		return blogRepository.Postfindbyno(postNo);
	}

	
	
}
