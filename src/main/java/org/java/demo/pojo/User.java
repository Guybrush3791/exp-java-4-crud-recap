package org.java.demo.pojo;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private LocalDate subscriptionDate;
	@Past
	@NotNull
	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() { }
	public User(String username, String password, 
			LocalDate subscriptionDate, LocalDate dateOfBirth) {
		
		setUsername(username);
		setPassword(password);
		setSubscriptionDate(subscriptionDate);
		setDateOfBirth(dateOfBirth);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(LocalDate subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getUsername() + " - " + getPassword()
			+ "\nsubscription: " + getSubscriptionDate()
			+ "\ndate of birth: " + getDateOfBirth();
	}
}
