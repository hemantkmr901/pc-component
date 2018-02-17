package com.unixon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "user")
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	@Size(max = 20, min = 3, message = "{user.name.invalid}")
	private String name;

	@Column(name = "email", unique = true)
	@Email(message = "{user.email.invalid}")
	@NotBlank(message = "{user.email.blank}")
	private String email;
	
	@Column(name="photoLink")
	private String photoLink;
	
//	@Transient
//	private MultipartFile file;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", photoLink=" + photoLink + "]";
	}

//	public MultipartFile getFile() {
//		return file;
//	}
//
//	public void setFile(MultipartFile file) {
//		this.file = file;
//	}
	
	
	}
