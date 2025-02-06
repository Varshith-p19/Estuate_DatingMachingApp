package com.dating.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dating.entities.ProcessingUser;
import com.dating.entities.User;
import com.dating.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	

	@GetMapping("/")
	public String home() {
		return "index";
	}


	@GetMapping("/register")
	public String signupPage() {
		return "register";
	}
	
	
	@GetMapping("/match_user")
	public String matchUser() {
		return "match_user";
	}
	
	@PostMapping("/match_user_process")
	public String matchUserProcess(@ModelAttribute ProcessingUser user,Model model) {
		List<User> users=userService.filterUserAccordingToPreference(user);
        System.out.println(users);
        model.addAttribute("users",users);
		return "show_matches";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) throws IOException {

		User saveUserDetails = userService.saveUser(user);

		if (!ObjectUtils.isEmpty(saveUserDetails)) {
			session.setAttribute("succMsg", "Registeration Succesfull");

		} else {
			session.setAttribute("errorMsg", "Registration not done Please Try again");
		}
		return "redirect:/register";
	}

	// getting the top matches user from that database.
	@GetMapping("/users")
	public List<User> users() {
		return new ArrayList<User>();
	}

}
