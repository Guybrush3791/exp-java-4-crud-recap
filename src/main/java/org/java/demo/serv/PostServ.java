package org.java.demo.serv;

import java.util.List;

import org.java.demo.pojo.Post;
import org.java.demo.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServ {

	@Autowired
	private PostRepo postRepo;
	
	public List<Post> findAll() {
		
		return postRepo.findAll();
	}
	public Post save(Post post) {
		
		return postRepo.save(post);
	}
}
