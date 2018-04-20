package com.newer.petstore.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.newer.petstore.domain.Account.Address;

/**
 * 订单
 * 
 * @author wtao
 *
 */
public class Order {

	// 编号
	private int id;
	// 时间
	private Date time;

	// 送货地址（如果为空，使用账号的默认地址）
	private Address address;
	// 订单项
	private List<Item> items = new ArrayList<>();
	// 总价
	private BigDecimal total;

	public Order() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
