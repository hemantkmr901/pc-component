package com.unixon.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.unixon.model.User;
import com.unixon.service.UserService;

@Controller
public class SaveController {

	@Autowired
	private UserService userService;

	private String email = null;

	@GetMapping("/userForm")
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.getListService());
		return "userForm";
	}

	@PostMapping("/saveText")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		// This will check validation
		if (result.hasErrors()) {
			model.addAttribute("users", userService.getListService());
			return "userForm";
		} else
		// If no error found then we store User object into serviceImp class for a while
		{
			userService.setUser(user);
			return "fileUploadForm";
		}
	}

	// In this handler Method we getting file from fileUploadForm.jsp then --
	// Uploading it by uploadFile() after that getting User from serviceImp class
	// then set--
	// photoLink in user object then calling saveService() to store in db.
	@PostMapping("/saveFileUpload")
	public String saveImageWithData(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		String photoLink;
		if (file.getOriginalFilename().isEmpty()) {
			model.addAttribute("msg", "Please Select File");
			return "fileUploadForm";
		} else {
			photoLink = this.uploadFile(file);

		}
		User user = userService.getUser();
		user.setPhotoLink(photoLink);
		userService.saveService(user);
		userService.setUser(null);
		model.addAttribute("msg", "Data Uploaded Successfully");
		return "success";
	}

	// Update Methods
	@GetMapping("/getById*")
	public String getById(@ModelAttribute("id") long id, Model model) {
		User user = userService.getUserByIdService(id);
		email = user.getEmail();
		model.addAttribute("user", user);
		return "update";
	}

	@PostMapping("/textUpdate*")
	public String textUpdate(@ModelAttribute("user") @Valid User userUpdate, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("user", userUpdate);
			return "update";
		} else {
			userService.setUser(userUpdate);
			return "fileUpdate";
		}

	}

	@PostMapping("/fileUpdate")
	public String fileUpdate(@RequestParam("file") MultipartFile file) throws IOException {
		if (!file.getOriginalFilename().isEmpty()) {
			//uploading photo
			userService.getUser().setPhotoLink(this.uploadFile(file));
			} 
		return fileUpdateSub();
	}

	
	@GetMapping("/deleteById*")
	public String deleteById(@RequestParam("id") String id)
	{
		userService.deleteService(Long.parseLong(id));
		return "index";
	}
	
	
	
	
	
	
	// For File Uploading in Server
	private String uploadFile(MultipartFile file) throws IOException {
		String path = file.getOriginalFilename().replace("\\", "/");
		String filename = path.substring(path.lastIndexOf("/")+1);
		BufferedOutputStream bout = new BufferedOutputStream(
				(new FileOutputStream(new File("/var/lib/mysql/data/",filename))));	
		bout.write(file.getBytes());
		bout.flush();
		bout.close();
		return "D:/fileUploadForm/"+filename;
	}
	
	private String fileUpdateSub()
	{	
		if (userService.getUser().getEmail().equals(email)) {
			userService.getUser().setEmail(null);
			userService.updateService(userService.getUser().getId(), userService.getUser());
		}else
		{
			userService.updateService(userService.getUser().getId(), userService.getUser());
		}
		userService.setUser(null);
		return "index";
	}
	
}
