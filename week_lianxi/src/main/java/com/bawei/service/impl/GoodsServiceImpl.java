package com.bawei.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.bawei.entity.Goods;
import com.bawei.service.GoodsService;
import com.github.pagehelper.PageInfo;
@Service
public class GoodsServiceImpl implements GoodsService {

	
	@Resource
	private RedisTemplate<String, Goods> redisTemplate;
	@Override
	public PageInfo<Goods> list(Integer pageNum, int pageSize) {

		ListOperations<String, Goods> opsForList = redisTemplate.opsForList();
		
		List<Goods> list = opsForList.range("goods_list", (pageNum-1)*pageSize, pageNum*pageSize-1);
		
		PageInfo<Goods> pageInfo = new PageInfo<>(list);
		
		//获取数据的总条数
		Long total = opsForList.size("goods_list");
		
		pageInfo.setTotal(total);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		
		//总页数
		int pages =(total.intValue()+pageSize-1)/pageSize;
		pageInfo.setPages(pages);
		pageInfo.setPrePage(pageNum<=1?1:(pageNum-1));
		pageInfo.setNextPage(pageNum>=pages?pages:(pageNum+1));
		return pageInfo;
	}
	@Override
	public PageInfo<Goods> setList(Integer pageNum, int pageSize) {

		ZSetOperations<String, Goods> opsForZSet = redisTemplate.opsForZSet();
		
		Set<Goods> reverseRange = opsForZSet.reverseRange("goods_zset",(pageNum-1)*pageSize, pageNum*pageSize-1);
		
		List<Goods> arrayList = new ArrayList<>(reverseRange);
		
		PageInfo<Goods> pageInfo = new PageInfo<>(arrayList);
		//获取数据的总条数
		Long total = opsForZSet.size("goods_zset");
		pageInfo.setTotal(total);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		
		//总页数
		int pages =(total.intValue()+pageSize-1)/pageSize;
		pageInfo.setPages(pages);
		pageInfo.setPrePage(pageNum<=1?1:(pageNum-1));
		pageInfo.setNextPage(pageNum>=pages?pages:(pageNum+1));
		return pageInfo;
	}
	
	
	
	
}
