package com.douzone.jblog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void categoryinsert(UserVo vo) {
		sqlSession.insert("category.insert", vo);
	}
	
	public List<CategoryVo> findcategory(String id) {
		return sqlSession.selectList("category.findAll",id);
	}
	
	public void insertCate(CategoryVo vo) {
		sqlSession.insert("category.insertCategory", vo);
	}
	
	public void deleteByCategory(Long no) {
		sqlSession.delete("category.deleteByCategory",no);
	}

	public void findByCategoryNo(Long pathNo1) {
		sqlSession.selectOne("category.findByCategoryNo",pathNo1);
	}
}
