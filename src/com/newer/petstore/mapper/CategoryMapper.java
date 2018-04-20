package com.newer.petstore.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.newer.petstore.domain.Category;
import com.newer.petstore.domain.Product;

/**
 * 产品分类的数据操作
 * 
 * @author wtao
 *
 */
public interface CategoryMapper {

	@Select("select * from category order by title")
	List<Category> findAll();

	/**
	 * 获得特定分类的商品列表
	 * 
	 * @param title
	 *            分类的名称
	 * @return List<Product> 该分类中的所有商品
	 */
	@Select("select * from v_product_category where c_title = #{title}")
	List<Product> getProductsByCategory(String title);

}
