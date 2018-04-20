package com.newer.petstore.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newer.petstore.AppInfo;
import com.newer.petstore.domain.Cart;
import com.newer.petstore.domain.Product;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 每次【加入购物车】操作 HTTP GET 请求，携带一个查询参数 id
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		switch (action) {
		case "delete":
			doDelete(request, response);
			break;
		case "add":
			doAdd(request, response);
			break;
		default:
			break;
		}
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void doDelete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		// 从购物车清除
		System.out.println("doDelete");
		
		String idValue = request.getParameter("id");
		int id = Integer.parseInt(idValue);
		
		Product product = new Product();
		product.setId(id);
		
		// 购物车
		Cart cart = (Cart) request.getSession().getAttribute(AppInfo.SESSION_CART);
		cart.delete(product);
		
		
		// 响应 json/xml 格式数据
		response.setContentType("application/json");
		
		// 回复的数据
		HashMap<String, Object> data = new HashMap<>();
		data.put("result", "ok");
		Gson gson = new Gson();
		// 数据序列化为 json 格式
		String json = gson.toJson(data);
		
		// 从响应对象获得输出流
		response.getWriter().write(json);
	}
	

	/**
	 * 加入购物车
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// HTTP 的参数是文本格式（字符串）
		String idValue = request.getParameter("id");
		int id = Integer.parseInt(idValue);

		List<Product> list = (List<Product>) getServletContext().getAttribute(AppInfo.APP_PRPDUCT_LIST);

		Product product = null;
		for (Product p : list) {
			if (p.getId() == id) {
				product = p;
			}
		}

		// 加入购物车
		Cart cart = (Cart) request.getSession().getAttribute(AppInfo.SESSION_CART);
		cart.addCart(product);

		// 方案一：页面的跳转（请求转发、响应重定向）
		// 响应重定向
		// response.sendRedirect("cart.jsp");

		// 方案二：直接回复数据
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		// 数据序列化 json
		HashMap<String, Object> map = new HashMap<>();
		map.put("result", "已放入购物车");
		map.put("resultCode", 1);
		String json = new Gson().toJson(map);

		// 发送 json 数据
		response.getWriter().write(json);
	}

}
