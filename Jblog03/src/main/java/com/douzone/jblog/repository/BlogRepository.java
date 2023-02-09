package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo findblog(String id) {
		return sqlSession.selectOne("blog.find",id);
	}
	
	public void bloginsert(UserVo vo) {
		sqlSession.insert("blog.insert", vo);
	   }
	
//	public void insertByUrl(BlogVo vo) {
//		sqlSession.insert("blog.insertByUrl",vo);
//	}
	
	public void updateblog(BlogVo vo) {
		sqlSession.update("blog.updateblog",vo);
	}
		
	public void insertPost(PostVo vo) {
		sqlSession.insert("post.insertPost",vo);
	}
	
	public List<PostVo> findPost() {
		return sqlSession.selectList("post.findAll");
	}
}
