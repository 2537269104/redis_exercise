package com.bawei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bawei.entity.Goods;
import com.bawei.service.GoodsService;
import com.github.pagehelper.PageInfo;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
    
	@RequestMapping("/list")
	public String list(Model model,@RequestParam(defaultValue = "1")Integer pageNum) {
		
		PageInfo<Goods> pageInfo = goodsService.list(pageNum,10);
		
		model.addAttribute("pageInfo", pageInfo);
		return "list";
	}
	
	
	@RequestMapping("/setList")
	public String setList(Model model,@RequestParam(defaultValue = "1")Integer pageNum) {
		
		
		PageInfo<Goods> pageInfo = goodsService.setList(pageNum,10);
		
		model.addAttribute("pageInfo", pageInfo);
		
		return "setList";
	}
}
