/*package com.zensar.form.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zensasr.form.model.User;
import com.zensar.form.service.UserService;

@Controller
public class UserFormController {

	private final Logger logger = LoggerFactory.getLogger(UserFormController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateUserForm() : {}", id);

		User user = userService.findById(id);
		
		model.addAttribute("userFormModel", user);
		model.addAttribute("title", "Update User");
		model.addAttribute("action", "Update");
		
		return "user/userform";

	}
	
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {

		logger.debug("showAddUserForm()");

		User user = new User();
		
		//set default value
		user.setuserName("kiran");
		user.setBirthdayDate("02-02-1985");
		
		
		model.addAttribute("userFormModel", user);
		model.addAttribute("message", "birthdayremainder");
		
		return "user/userform";

	}

	

	
	
	
	
}*/
