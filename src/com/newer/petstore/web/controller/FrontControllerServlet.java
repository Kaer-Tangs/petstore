package com.newer.petstore.web.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.newer.petstore.AppInfo;

/**
 * 前端控制器（拦截所有的请求）
 * 
 * @author wtao
 *
 */
public class FrontControllerServlet extends HttpServlet {

	HashMap<String, Class> map = new HashMap<>();

	/**
	 * 数据库的会话工厂
	 */
	SqlSessionFactory factory;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// 设置 URL 和 Controller 的映射（使用注解）
		map.put("/register.action", RegisterController.class);
		map.put("/login.action", LoginController.class);
		map.put("/checkName.action", CheckAccountController.class);
		// map.put("", RegisterController.class);
		// map.put("", RegisterController.class);

		// 获得会话工厂
		factory = (SqlSessionFactory) getServletContext().getAttribute(AppInfo.APP_SESSION_FACTORY);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /petstore/user/login.action
		String url = request.getRequestURI();
		String urlName = url.substring(url.lastIndexOf("/"));

		// 从映射中获得 url 对于的 控制器类
		Class controllerClass = map.get(urlName);
		if (controllerClass != null) {
			try {
				// 使用反射创建该类型的实例: 基于接口编程
				Controller controller = (Controller) controllerClass.newInstance();

				// 调用执行
				String result = controller.execute(request, response, factory.openSession());

				if (result.startsWith("ajax:")) {
					String data = result.substring(5);

					// 直接只用响应对象发送数据
					// response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(data);
				} else {
					// 响应的重定向
					response.sendRedirect(result);
				}

			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("error/404.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 调用 doGet
		doGet(request, response);
	}

}
