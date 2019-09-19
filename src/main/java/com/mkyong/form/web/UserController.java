package com.zensar.form.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import com.zensar.form.model.User;
import com.zensar.form.service.UserService;
import com.zensar.form.validator.UserFormValidator;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserFormValidator userFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/users";
	}

	
	// save or update user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateUser() : {}", user);

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "users/userform";
		} else {

			userService.saveOrUpdate(user);

					
			 String dateStr_1 ="02-May-1991";
 String username="kiran";
 String birthdayy;
       LocalDate today = LocalDate.now();
  DateTimeFormatter formatter_1=DateTimeFormatter.ofPattern("dd-MMM-yyyy");
  LocalDate birthday= LocalDate.parse(dateStr_1,formatter_1);
      // LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);

       LocalDate nextBDay = birthday.withYear(today.getYear());

       //If your birthday has occurred this year already, add 1 to the year.
       if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
           nextBDay = nextBDay.plusYears(1);
       }

       Period p = Period.between(today, nextBDay);
       long p2 = ChronoUnit.DAYS.between(today, nextBDay);
       //System.out.println("There are " + p.getMonths() + " months, and " +
                        //  p.getDays() + " days until your next birthday. (" +
                         // p2 + " total)");

       birthdayy="There ae "+p.getMonths() +"months,and"+ p.getDays() +"days until your next birthday";
       System.out.println(birthdayy);
			
				
			// POST/REDIRECT/GET
			return "redirect:/users/" + user.getuserName();

			// POST/FORWARD/GET
			// return "user/list";

		}

	}

	// show add user form
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {

		logger.debug("showAddUserForm()");

		User user = new User();

		// set default value
		user.setName("mkyong123");
		user.setEmail("test@gmail.com");
		user.setAddress("abc 88");
		//user.setPassword("123");
		//user.setConfirmPassword("123");
		user.setNewsletter(true);
		user.setSex("M");
		user.setFramework(new ArrayList<String>(Arrays.asList("Spring MVC", "GWT")));
		user.setSkill(new ArrayList<String>(Arrays.asList("Spring", "Grails", "Groovy")));
		user.setCountry("SG");
		user.setNumber(2);

		model.addAttribute("userForm", user);

		populateDefaultModel(model);

		return "users/userform";

	}

	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateUserForm() : {}", id);

		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		
		populateDefaultModel(model);
		
		return "users/userform";

	}

	
	// show user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {

		logger.debug("showUser() id: {}", id);

		User user = userService.findById(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "users/show";

	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("user/show");
		model.addObject("msg", "user not found");

		return model;

	}

}
