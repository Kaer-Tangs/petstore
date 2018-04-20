package com.newer.petstore.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.petstore.domain.Account;

public interface AccountMapper {

	/**
	 * 创建账号
	 * 
	 * @param account
	 *            账号对象
	 */
	@Insert("insert into accounts(name,password) values(#{name}, #{password})")
	void create(Account account);

	/**
	 * 登录
	 * 
	 * @param user
	 *            用户名
	 * @return 用户的密码或 null
	 */
	@Select("select * from accounts where name = #{user}")
	Account login(String user);

	/**
	 * 检查用户名是否存在
	 * 
	 * @param name
	 * @return
	 */
	@Select("select password from accounts where name=#{n}")
	String checkName(@Param("n") String name);

}
