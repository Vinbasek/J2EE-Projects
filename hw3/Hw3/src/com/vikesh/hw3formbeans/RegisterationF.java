// Author :Vikesh Inbasekharan
// Date : 30 - 09 - 2016
// course : 08-672

package com.vikesh.hw3formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RegisterationF {
	private String EmailAddress;
	private String FirstName;
	private String LastName;
    private String Password;
    private String button;
    
    
    //Build a constructor
    public RegisterationF(HttpServletRequest request) {
		EmailAddress = request.getParameter("email");;
		FirstName = request.getParameter("firstName");
		LastName = request.getParameter("lastName");
		Password = request.getParameter("password");
		this.button = request.getParameter("button");
	}

  //Getter and setter
  	public String getEmailAddress() {return EmailAddress;}
  	public String getFirstName() {return FirstName;}
  	public String getLastName() {return LastName;}
  	public String getPassword() {return Password;}
  	public String getButton() {return button;}

  	public void setEmailAddress(String emailAddress) {EmailAddress = emailAddress;}
  	public void setFirstName(String firstName) {FirstName = firstName;}
  	public void setLastName(String lastName) {LastName = lastName;}
  	public void setPassword(String password) {Password = password;}
  	public void setButton(String button) {this.button = button;}

	
  	public boolean isPresent() {return button!=null;}
	//Input Validation 
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (EmailAddress == null || EmailAddress.length() == 0) errors.add("Email address is required");
        if (FirstName == null || FirstName.length() == 0) errors.add("First name is required");
        if (LastName == null || LastName.length() == 0) errors.add("Last name  is required");
        if (Password == null || Password.length() == 0) errors.add("Password is required");
        if (button == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;

        if (!button.equals("Register1")) errors.add("Invalid button");
        if (EmailAddress.matches(".*[<>\"].*")) errors.add("Email Address may not contain special characters");
		
        return errors;
    }

	
	
    

}
