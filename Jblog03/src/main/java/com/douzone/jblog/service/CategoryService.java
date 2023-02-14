package com.douzone.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> findCategory(String id) {
		return categoryRepository.findcategory(id);
	}
	
	public void insertUser(CategoryVo vo) {
		categoryRepository.insertCate(vo);
	}
	
	public void deleteCategory(Long no) {
		categoryRepository.deleteByCategory(no);
	}


	
	public List<CategoryVo> countPostNo(String id) {
		return categoryRepository.countPostNo(id);
	}
}
