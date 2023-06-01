package org.java.demo;

import java.time.LocalDate;

import org.java.demo.pojo.Post;
import org.java.demo.pojo.User;
import org.java.demo.serv.PostServ;
import org.java.demo.serv.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Crud2Application implements CommandLineRunner {

	@Autowired
	private UserServ userServ;
	
	@Autowired
	private PostServ postServ;
	
	public static void main(String[] args) {
		SpringApplication.run(Crud2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User("Pluto", "Threepwood", 
				LocalDate.parse("2023-01-01"), LocalDate.parse("1988-01-01"));
		User u2 = new User("Marco", "Rossi", 
				LocalDate.parse("2023-02-02"), LocalDate.parse("1999-05-05"));
		User u3 = new User("Francesca", "Bianchi", 
				LocalDate.parse("2023-02-15"), LocalDate.parse("1995-06-01"));
		
		userServ.save(u1);
		userServ.save(u2);
		userServ.save(u3);
		
		Post p1 = new Post("mio titolo 1", "mio testo 1", 
				LocalDate.parse("2023-05-01"), u1);
		Post p2 = new Post("mio titolo 2", "mio testo 2", 
				LocalDate.parse("2023-05-02"), u2);
		Post p3 = new Post("mio titolo 3", "mio testo 3", 
				LocalDate.parse("2023-05-03"), u3);
		Post p4 = new Post("mio titolo 4", "mio testo 4", 
				LocalDate.parse("2023-05-04"), u1);
		Post p5 = new Post("mio titolo 5", "mio testo 5", 
				LocalDate.parse("2023-05-05"), u1);
		
		postServ.save(p1);
		postServ.save(p2);
		postServ.save(p3);
		postServ.save(p4);
		postServ.save(p5);
	}
}
