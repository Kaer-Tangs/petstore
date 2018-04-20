package com.newer.petstore.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * 分类（名称、很多商品）
 * 
 * @author wtao
 *
 */
public class Category {

	// 编号
	private int id;

	// 分类的名称
	private String title;

	// 该分类的所有商品
	private List<Product> productList = new LinkedList<>();

	public Category() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	/**
	 * 在分类中增加新的产品
	 * 
	 * @param product
	 */
	public void addProduct(Product product) {
		productList.add(product);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + "]";
	}

}
