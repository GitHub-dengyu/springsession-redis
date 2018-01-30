package com.huato.redissession.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.huato.redissession.bean.User;

@Controller
@SessionAttributes("user")  //这告诉你存储的session对象的名字
public class IndexController {
	 @Autowired
	 RedisTemplate<String, String> redisTemplate;
/*	   
 *      方式1  基于session容器的方式
 *     @RequestMapping(value = "/login/{username}")
	    public String login(HttpSession session,@PathVariable("username") String username){

	        session.setAttribute("user", JSON.toJSON((new User(username,"123456"))));
	        
	        return "login";
	    }

	    @RequestMapping(value = "/")
	    public String index(HttpSession session, Model model){
	        User user = JSON.parseObject(session.getAttribute("user").toString(), User.class);
	        model.addAttribute("user", user);
	        return "index";
	    }*/
	    

	   //方式2  使用SessionAttrubites和ModelAttribute注解方式
	   @RequestMapping(value = "/login/{username}")
	    public String login(  Model model,@PathVariable("username") String username){
		   //存储的是json对象 这样数据在session中
	    	model.addAttribute("user", JSON.toJSON((new User(username,"123456"))));
	        return "login";
	    }
	    @RequestMapping(value = "/")  
	    public String index(@ModelAttribute("user") User user){
	    	String boundKey = "key1";
	    	BoundValueOperations<String,String> ops = redisTemplate.boundValueOps(boundKey);//绑定key
	    	ops.set("hellos");//添加数据，相当于redis命令:set key1 hellos,只是在这里不需要显式使用key了
	    	System.out.println(ops.get());//获取并且输出数据
		    return "index";
	    } 
	    
	    
}
