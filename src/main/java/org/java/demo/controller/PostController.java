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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostServ postServ;
	
	@Autowired
	private UserServ userServ;
	
	@GetMapping
	public String getPostIndex(Model model) {
		
		List<Post> posts = postServ.findAll();
		model.addAttribute("posts", posts);
		
		return "post-index";
	}
	
	@GetMapping("/create")
	public String getPostCreateForm(Model model) {
		
		List<User> users = userServ.findAll();
		
		model.addAttribute("post", new Post());
		model.addAttribute("users", users);
		
		return "post-create";
	}
	@PostMapping("/create")
	public String storeNewPost(
			Model model,
			@Valid @ModelAttribute Post post,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			List<User> users = userServ.findAll();
			
			model.addAttribute("post", post);
			model.addAttribute("users", users);
			
			return "post-create";
		}
		
		post.setDate(LocalDate.now());
		
		postServ.save(post);
		
		return "redirect:/posts";
	}
}
