package com.newer.petstore;

/**
 * 应用程序中的常量字段（作用域中对象的 key）
 * 
 * @author wtao
 *
 */
public interface AppInfo {

	/**
	 * 会话工厂的名字
	 */
	String APP_SESSION_FACTORY = "factory";

	/**
	 * 产品分类列表
	 */
	String APP_CATEGORY_LIST = "categories";

	/**
	 * 产品列表
	 */
	String APP_PRPDUCT_LIST = "products";

	/**
	 * 通过这个标签获得购物车（从 HttpSession 作用域）
	 */
	String SESSION_CART = "cart";

}
