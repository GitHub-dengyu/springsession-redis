package com.huato.redissession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	 @Autowired
	 RedisTemplate<String, String> redisTemplate;
	
	
	   @RequestMapping(value = "/hello.do")  
	   @ResponseBody
	    public String hello(){
	    	//这里在测试操作redis
	    	String boundKey = "key1";
	    	BoundValueOperations<String,String> ops = redisTemplate.boundValueOps(boundKey);//绑定key
	    	ops.set("hellos");//添加数据，相当于redis命令:set key1 hellos,只是在这里不需要显式使用key了
	    	System.out.println(ops.get());//获取并且输出数据
		    return ops.get();
	    } 
	    
}
