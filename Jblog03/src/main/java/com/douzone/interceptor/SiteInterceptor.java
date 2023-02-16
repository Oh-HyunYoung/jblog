package com.douzone.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

//import com.douzone.mysite.service.SiteService;
//import com.douzone.mysite.vo.SiteVo;
//
//public class SiteInterceptor implements HandlerInterceptor {
//	@Autowired
//	private ServletContext servletContext;
//	
//	@Autowired
//	private SiteService siteService;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		SiteVo siteVo = siteService.getSite();
//		servletContext.setAttribute("siteVo", siteVo);
//		
//		// other code
////		SiteVo siteVo = (SiteVo)request.getServletContext().getAttribute("sitevo");
////		if(siteVo == null) {
////			siteService.getSite();
////			request.getServletContext().setAttribute("siteVo",siteVo);
////		}
//		return true;
//	}
//
//}
