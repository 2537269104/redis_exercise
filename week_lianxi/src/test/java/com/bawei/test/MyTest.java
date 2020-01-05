package com.bawei.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.entity.Goods;
import com.liuhao.util.StreamUtil;
import com.liuhao.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class MyTest {

	@Resource
	private RedisTemplate<String, Goods> redisTemplate;
	
	@Test
	public void test() {
		
		ListOperations<String, Goods> opsForList = redisTemplate.opsForList();
		ZSetOperations<String, Goods> opsForZSet = redisTemplate.opsForZSet();
		//读文件
		List<String> readTextFileByLine = StreamUtil.readTextFileByLine(new File("D:\\soft\\workspaces\\CMS\\week_lianxi\\src\\test\\resources\\readme.txt"));
		for (String readText : readTextFileByLine) {
			System.out.println(readText);
		//切分数据
		 String[] s = readText.split("==");
		 Goods goods = new Goods();
		 //ID值要使用isNumber()工具方法判断是不是数字
		 if(StringUtil.isNumber(s[0])) {
			 goods.setId(Integer.parseInt(s[0]));
		 }
		 //商品名称要使用hasText()方法判断有没有值
		 if(!StringUtil.isBlank(s[1])) {
			 goods.setName(s[1]);
		 }
		 //价格要使用hasText()方法判断有没有值，并使用isNumber()判断是不是数字（不是数字的可以手工处理好再解析）。
		 //然后去掉“¥”符号，再转成数字。
		 if(!StringUtil.isBlank(s[2])) {
			 String subStr = s[2].substring(1);
			 if(StringUtil.isNumber(subStr)) {
				 goods.setPrice(new BigDecimal(subStr));
			 } else {
				 goods.setPrice(new BigDecimal(0));
			 }
		 }
		//百分比使用hasText()方法判断有没有值，如果没值则默认为0，
		 //并使用isNumber()判断是不是数字。然后去掉“%”符号，再转成数字
		 if(s.length == 4) {
			 if(!StringUtil.isBlank(s[3])) {
				 String subStr1 = s[3].replace("%","");
				 if(StringUtil.isNumber(subStr1)) {
					 goods.setSell(Double.parseDouble(subStr1));
			 }
			 }
			 else {
				 goods.setSell(0.0);
			 }
		 }
		 else {
			 goods.setSell(0.0);
		 }
		 System.out.println(goods);
		 opsForList.leftPush("goods_list", goods);
		 opsForZSet.add("goods_zset", goods, goods.getSell());
		}
		
	}
}
