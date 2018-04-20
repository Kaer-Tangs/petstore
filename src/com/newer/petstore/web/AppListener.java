package com.newer.petstore.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.newer.petstore.AppInfo;
import com.newer.petstore.domain.Category;
import com.newer.petstore.mapper.CategoryMapper;

/**
 * 监听器（特定事件发生时，和应用程序的上下文）
 * 
 * @author wtao
 *
 */
//@WebListener
public class AppListener implements ServletContextListener {

	private SqlSessionFactory factory;

	/**
	 * 销毁（程序推出）由容器调用（回调 callback）
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO

	}

	/**
	 * 应用程序的初始化(代码重构)
	 */
	public void contextInitialized(ServletContextEvent e) {
		// Java Web 获得上下文
		ServletContext application = e.getServletContext();

		factory = initSqlSessionFactory(application);
		application.setAttribute(AppInfo.APP_SESSION_FACTORY, factory);
//		application.removeAttribute(name);
		
		loadCategoryList(application);

		loadProductList(application);
	}

	private void loadProductList(ServletContext application) {

	}

	/**
	 * 创建会话工厂: 加载数据源（myBatis 中的会话工厂就是一个数据源）
	 * 
	 * @param application
	 */
	private SqlSessionFactory initSqlSessionFactory(ServletContext application) {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("config.xml");
			return new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e1) {
			return null;
		}
	}

	/**
	 * 加载产品分类列表
	 * 
	 * @param application
	 * @param factory
	 */
	private void loadCategoryList(ServletContext application) {
		SqlSession sqlSession = factory.openSession();
		// 会话执行数据操作
		CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
		// 从数据库查询获得全局的数据信息
		List<Category> c = categoryMapper.findAll();
		// 存储在全局作用域
		application.setAttribute(AppInfo.APP_CATEGORY_LIST, c);
		System.out.println("应用上下文初始化完毕：" + c);

		sqlSession.commit();
		sqlSession.close();
	}

}
