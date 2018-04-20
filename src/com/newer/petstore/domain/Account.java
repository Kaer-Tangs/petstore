package com.newer.petstore.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 账号
 * 
 * @author wtao
 *
 */
public class Account {

	// 基本信息
	private int id;
	private String name;
	private String password;
	private List<Address> addressList = new ArrayList<>();

	// 购物车
	private Cart cart;

	// 订单
	private List<Order> orderList = new LinkedList<>();

	public Account() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", password=" + password + "]";
	}



	/**
	 * 账号的地址（内聚）封装
	 * 
	 * @author wtao
	 *
	 */
	public static class Address {

		String province;
		String city;
		String street;
		String name;
		String phone;

		public Address() {
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

	}

}
