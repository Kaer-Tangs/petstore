package com.newer.petstore.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.newer.petstore.domain.Account;
import com.newer.petstore.mapper.AccountMapper;
import com.newer.petstore.util.EncodeUtil;

/**
 * 定义登录的操作流程
 * 
 * @author wtao
 *
 */
public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, SqlSession sqlSession)
			throws ServletException, IOException {

		String name = request.getParameter("account");
		
		// 明文
		String password = request.getParameter("password");

		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		
		// 从数据库查询 king
		Account account = mapper.login(name);
		
		if (account != null 
				&& account.getPassword().equals(EncodeUtil.md5(password))) {
			// 用户名、密码匹配
			request.getSession().setAttribute("account", account);
			return "cart.jsp";
		} else {
			// 不匹配
			request.getSession().setAttribute("errorMessage", "用户名、密码不匹配");
			return "account.jsp";
		}

	}

}
