package com.newer.petstore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.google.gson.Gson;
import com.newer.petstore.AppInfo;
import com.newer.petstore.domain.Product;
import com.newer.petstore.mapper.ProductMapper;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 接收并响应 GET 请求
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		String key = request.getParameter("keyWords");
		
		SqlSessionFactory factory = (SqlSessionFactory) getServletContext()
				.getAttribute(AppInfo.APP_SESSION_FACTORY);
		
		ProductMapper mapper = factory.openSession()
				.getMapper(ProductMapper.class);
		
		List<Product> list =  mapper.search(key);
		
		// 在视图中显示搜索的结果
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		// 响应的数据是 json 格式
//		response.setContentType("text/html");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
