package com.newer.petstore.domain;

import java.io.Serializable;

/**
 * 所有实体类的基类（android/SQLite ）
 * 
 * @author wtao
 *
 */
public abstract class BaseEntity implements Serializable {

	/**
	 * 主键
	 */
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
