package com.pinyougou.sellgoods.service;

import java.util.List;



import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌接口
 * @author Steven.yu
 *
 */
public interface BrandService {

	/**
	 * 获取品牌列表
	 */
	public List<TbBrand> findAll();
	
	
	/**
	 * 按着指定页数进行分页
	 */
	public PageResult findByPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加品牌
	 */
	public boolean add(TbBrand brand);
	
	/**
	 * 修改品牌
	 */
	public boolean update(TbBrand brand);
	
	
	/**
	 * 根据品牌id获取品牌实体
	 */
	public TbBrand findOne(Long id);

	/**
	 * 根据id批量对品牌记录进行删除
	 */
	public boolean delete(Long[] ids);


	/**
	 * 根据条件进行模糊查询并分页
	 * @param pageNum
	 * @param pageSize
	 * @param brand
	 * @return
	 */
	public PageResult search(int pageNum, int pageSize, TbBrand brand);
}
