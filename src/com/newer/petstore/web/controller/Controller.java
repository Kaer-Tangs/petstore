package com.newer.petstore.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

/**
 * 控制器的定义（流程的规范化）
 * 
 * @author wtao
 *
 */
public interface Controller {

	/**
	 * 
	 * @param request
	 * @param response
	 * @param sqlSession
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	String execute(
			HttpServletRequest request, 
			HttpServletResponse response, 
			SqlSession sqlSession)
			throws ServletException, IOException;
}
