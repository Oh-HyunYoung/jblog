package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertUser(UserVo vo) {
		userRepository.insert(vo);
		blogRepository.bloginsert(vo);
		categoryRepository.categoryinsert(vo);
	}
	
	public UserVo getUser(UserVo vo) {
		return userRepository.login(vo.getId(),vo.getPassword());
	}
	
}
