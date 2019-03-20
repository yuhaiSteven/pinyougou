package com.pinyougou.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellgoods.service.BrandService;

import entity.PageResult;
import entity.Result;

@RequestMapping("/brand")
@RestController
public class BrandController {
	
	@Reference
	private BrandService brandService;

	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		return brandService.findAll();
	}
	
	
	@RequestMapping("/findByPage")
	public PageResult findByPage(int page,int rows){
		return brandService.findByPage(page, rows);
	}
	
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand brand){
		try {
			 brandService.add(brand);
				return new Result(true, "添加成功...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "添加失败...");
		}
	}
	
	
	@RequestMapping("/update")
	public Result update(@RequestBody TbBrand brand){
		try {
			 brandService.update(brand);
			return new Result(true, "修改成功...");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new Result(false, "修改失败...");
		}
		
	}
	
	
	/**
	 * 根据品牌id获取品牌实体
	 */
	@RequestMapping("/findOne")
	public TbBrand findOne(Long id){
		return  brandService.findOne(id);
	}
	
	
	/**
	 * 根据id批量对品牌记录进行删除
	 */
	@RequestMapping(value="/delete",method={RequestMethod.GET})
	public Result delete(Long[] ids){
		try {
			brandService.delete(ids);
			return new Result(true, "删除成功...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "删除失败...");
		}
	}
	
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBrand brand,int page,int rows){
		return brandService.search(page, rows,brand);
	}
	
	
}
