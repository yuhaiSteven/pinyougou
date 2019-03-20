package com.pinyougou.sellgoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.sellgoods.service.SpecificationService;
import com.pojogroup.Specification;

import entity.PageResult;

/**
 * 服务实现层
 * 
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper
				.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		TbSpecification tbSpecification = specification.getSpecification();

		specificationMapper.insert(tbSpecification);
		Long id = tbSpecification.getId();// 获取对应的id对象
		// 获取列表
		List<TbSpecificationOption> specificationOptionList = specification
				.getSpecificationOptionList();
		// 循环列表
		for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
			tbSpecificationOption.setSpecId(id);
			specificationOptionMapper.insert(tbSpecificationOption);
		}

	}

	/**
	 * 修改,规格选项先执行删除再添加
	 */
	@Override
	public void update(Specification specification) {
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		// 获取到单头id
		Long id = tbSpecification.getId();
		// 创建规格选项example对象
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		// 创建离线查询对象
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example
				.createCriteria();
		// 给离线查询对象赋予属性值
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria specificationOptionCriteria = criteria
				.andSpecIdEqualTo(id);
		// 执行删除
		specificationOptionMapper.deleteByExample(example);

		// 获取列表
		List<TbSpecificationOption> specificationOptionList = specification
				.getSpecificationOptionList();
		// 循环列表
		for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
			tbSpecificationOption.setSpecId(id);
			specificationOptionMapper.insert(tbSpecificationOption);
		}

	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id) {
		TbSpecification specification = specificationMapper
				.selectByPrimaryKey(id);
		// 创建规格选项example对象
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		// 创建离线查询对象
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example
				.createCriteria();
		// 给离线查询对象赋予属性值
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria specificationOptionCriteria = criteria
				.andSpecIdEqualTo(id);
		List<TbSpecificationOption> specificationOptionList = specificationOptionMapper
				.selectByExample(example);
		return new Specification(specification, specificationOptionList);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			specificationMapper.deleteByPrimaryKey(id);
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria specificationOptionList = criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}
	}

	@Override
	public PageResult findPage(TbSpecification specification, int pageNum,
			int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbSpecificationExample example = new TbSpecificationExample();
		Criteria criteria = example.createCriteria();

		if (specification != null) {
			if (specification.getSpecName() != null
					&& specification.getSpecName().length() > 0) {
				criteria.andSpecNameLike("%" + specification.getSpecName()
						+ "%");
			}

		}

		Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper
				.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

}
