package org.java.demo.pojo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 5, max = 255)
	private String title;
	
	@Size(min = 5)
	@Column(columnDefinition = "TEXT")
	private String text;
	private LocalDate date;
	
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	
	public Post() { }
	public Post(String title, String text, LocalDate date, User user) {
		
		setTitle(title);
		setText(text);
		setDate(date);
		setUser(user);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		 
		return "[" + getId() + "] " + getTitle() 
			+ "\n" + getText()
			+ "\n" + getDate()
			+ "\nauthor: " + getUser().getUsername();
	}
}
