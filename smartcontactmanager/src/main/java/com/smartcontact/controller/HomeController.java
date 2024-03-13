package com.smartcontact.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontact.dao.UserRepository;
import com.smartcontact.entities.User;
import com.smartcontact.helper.Message;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {
	
	//To encrypt the password
	
	  @Autowired 
	  private BCryptPasswordEncoder passwordEncoder;
	 
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Home- Smart Contact manager");
		return "home";
	}


	@RequestMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title","About- Smart Contact manager");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title","Register- Smart Contact manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping(value="/do_register",method= RequestMethod.POST)
	public String registerUser(@Valid   @ModelAttribute("user") User user,BindingResult result1, @RequestParam(value="agreement",defaultValue = "false")boolean agreement,
			Model model,HttpSession session)
	{
		try {
			if(!agreement)
			{
				System.out.println("Terms and agreement not accepted.");
				throw new Exception("Terms and agreement not accepted.");
			}
			if(result1.hasErrors())
			{
				System.out.println("ERROR"+ result1.toString());
				model.addAttribute("user",user);
				return "signup";
			}
			//we are hard coding the values which we are not taking from front end .
			user.setRole("USER_ROLE");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			
			//encrypt the password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("Agreement "+ agreement);
			System.out.println("User"+ user);
			this.userRepository.save(user);
			model.addAttribute("user",new User());
			session.setAttribute("message", new Message("Successfully registered", "alert-success"));
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong"+e.getMessage(), "alert-danger"));
			return "signup";
		}
		return "signup";
		
	}
}
