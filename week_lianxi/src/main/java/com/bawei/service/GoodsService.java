package com.bawei.service;

import com.bawei.entity.Goods;
import com.github.pagehelper.PageInfo;

public interface GoodsService {

	PageInfo<Goods> list(Integer pageNum, int i);

	PageInfo<Goods> setList(Integer pageNum, int i);

}
