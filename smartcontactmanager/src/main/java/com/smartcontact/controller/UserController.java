package com.smartcontact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/index")
	public String dashboard()
	{
		// normal/user_dashboard is used because user_dashboard is present in normal folder of template..
		return "normal/user_dashboard";
	}

}
