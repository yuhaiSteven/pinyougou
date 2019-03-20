package com.pojogroup;

import java.io.Serializable;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;

/**
 * 规格组合实体类
 */
public class Goods implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private TbGoods goods;
	
	private TbGoodsDesc goodsDesc;

	public TbGoods getGoods() {
		return goods;
	}

	public void setGoods(TbGoods goods) {
		this.goods = goods;
	}

	public TbGoodsDesc getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(TbGoodsDesc goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
	
}
