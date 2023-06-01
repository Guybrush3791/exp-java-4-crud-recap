package org.java.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.java.demo.pojo.Post;
import org.java.demo.pojo.User;
import org.java.demo.serv.PostServ;
import org.java.demo.serv.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServ userServ;
	
	@Autowired
	private PostServ postServ;
	
	@GetMapping
	public String getUserIndex(Model model) {
		
		List<User> users = userServ.findAll();
		model.addAttribute("users", users);
		
		return "user-index";
	}
	@GetMapping("/create")
	public String getUserCreateForm(Model model) {
		
		model.addAttribute("user", new User());
		
		return "user-create";
	}
	@PostMapping("/create")
	public String storeNewUser(
			Model model,
			@Valid @ModelAttribute User user,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			model.addAttribute("user", user);
			
			return "user-create";
		}
		
		user.setSubscriptionDate(LocalDate.now());
		
		userServ.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/{id}/post/create")
	public String getPostUserCreateForm(
			Model model,
			@PathVariable int id
		) {
		
		User user = userServ.findById(id).get();
		
		model.addAttribute("post", new Post());
		model.addAttribute("user", user);
		
		return "user-post-create";
	}
	@PostMapping("/post/create")
	public String storeNewPost(
			Model model,
			@Valid @ModelAttribute Post post,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			User user = userServ.findById(post.getUser().getId()).get();
			
			model.addAttribute("post", post);
			model.addAttribute("user", user);
			
			return "user-post-create";
		}
		
		post.setDate(LocalDate.now());
		
		postServ.save(post);
		
		return "redirect:/posts";
	}
}
