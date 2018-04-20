package com.newer.petstore.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.newer.petstore.AppInfo;
import com.newer.petstore.domain.Account;
import com.newer.petstore.mapper.AccountMapper;
import com.newer.petstore.util.EncodeUtil;

/**
 * 定义注册的流程
 * 
 * @author wtao
 *
 */
public class RegisterController implements Controller {

//	// 依赖
//	private SqlSession sqlSession;
//
//	/**
//	 * 依赖注入
//	 * 
//	 * @param sqlSession
//	 */
//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}

	@Override
	public String execute(
			HttpServletRequest request, 
			HttpServletResponse response,
			SqlSession sqlSession)
			throws ServletException, IOException {

		String accountName = request.getParameter("account");
		String password = request.getParameter("password");

		Account account = new Account();
		account.setName(accountName);
		account.setPassword(EncodeUtil.md5(password));
		System.out.println(account);

		// 获得
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
		accountMapper.create(account);
		sqlSession.commit();
		sqlSession.close();

		return "account.jsp";
	}

}
