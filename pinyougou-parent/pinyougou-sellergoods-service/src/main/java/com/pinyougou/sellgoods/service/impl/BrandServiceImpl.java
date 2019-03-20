package com.pinyougou.sellgoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellgoods.service.BrandService;

import entity.PageResult;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private TbBrandMapper brandMapper;
	/**
	 * 获取品牌列表
	 */
	@Override
	public List<TbBrand> findAll() {
		// TODO Auto-generated method stub
		return brandMapper.selectByExample(null);
	}
	
	/**
	 * 按着指定页数进行分页
	 */
	@Override
	public PageResult findByPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		//1.根据pagehelper进行分页
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand> page= (Page<TbBrand>)brandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	

	@Override
	public boolean add(TbBrand brand) {
		// TODO Auto-generated method stub
		return brandMapper.insert(brand) > 0;
	}

	
	@Override
	public boolean update(TbBrand brand) {
		// TODO Auto-generated method stub
		return brandMapper.updateByPrimaryKey(brand)>0;
	}

	@Override
	public TbBrand findOne(Long id) {
		// TODO Auto-generated method stub
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		int rows = 0;
		// TODO Auto-generated method stub
		for (Long id : ids) {
			int row = brandMapper.deleteByPrimaryKey(id);
			if(row > 0){
				rows++;
			}
		}
		return rows > 0;
	}

	@Override
	public PageResult search(int pageNum, int pageSize, TbBrand brand) {
		// TODO Auto-generated method stub
		//使用pagehelper分页
		PageHelper.startPage(pageNum,pageSize);
		//创建离线查询对象
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		if(brand.getName()!=null && brand.getName().length()>0){
			criteria.andNameLike("%"+brand.getName()+"%");
		}
		if(brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
			criteria.andFirstCharEqualTo(brand.getFirstChar());
		}
		Page<TbBrand> page= (Page<TbBrand>)brandMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

}
