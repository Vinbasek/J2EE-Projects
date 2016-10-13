// Author :Vikesh Inbasekharan
// Date : 30 - 09 - 2016
// course : 08-672

package com.vikesh.hw3formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	private String EmailAddress;
    private String Password;
    private String button;
    
    //Build a constructor
	public LoginForm(HttpServletRequest request) {
		
		EmailAddress = request.getParameter("email");
		Password = request.getParameter("password");
		button = request.getParameter("button");
	}

	//Getter and setter
	public String getEmailAddress() {return EmailAddress;}
	public String getPassword() {return Password;}
	public String getButton() {return button;}

	public void setEmailAddress(String emailAddress) {EmailAddress = emailAddress;}
	public void setPassword(String password) {Password = password;}
	public void setButton(String button) {this.button = button;}
    //check if a button is selected , first time login condition
	public boolean isPresent() {return button!=null;}
	//Input Validation 
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (EmailAddress == null || EmailAddress.length() == 0) errors.add("Email address is required");
        if (Password == null || Password.length() == 0) errors.add("Password is required");
        if (button == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Login")) errors.add("Invalid button");
        if (EmailAddress.matches(".*[<>\"].*")) errors.add("Email Address may not contain special characters");
		
        return errors;
    }
    

}
