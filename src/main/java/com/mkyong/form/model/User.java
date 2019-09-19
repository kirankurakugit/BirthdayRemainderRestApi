package com.zensar.form.model;

import java.util.List;

public class User {
	

	// form:input - textbox
	String userName;
        
	Date  birthdayDate;
	// form:input - textbox
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Date getBirthdayDate(){
		this.birthdayDate=birthdayDate;
	}

	public void setBirthdayDate(birthdayDate birthdayDate){
		this.birthdayDate=birthdayDate;
		
	}

		
}
