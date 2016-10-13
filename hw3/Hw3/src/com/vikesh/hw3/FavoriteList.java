package com.vikesh.hw3;

// Author :Vikesh Inbasekharan
// Date : 30 - 09 - 2016
// course : 08-672

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import com.vikesh.hw3formbeans.*;
import com.vikesh.hw3JavaBeans.Favorite;
import com.vikesh.hw3JavaBeans.User;


public class FavoriteList extends HttpServlet{

	/**
	 * favorites list 
	 */
	private static final long serialVersionUID = 1L;
	
	   private UserDAO userDAO;
	   private FavoriteDAO fDAO;

	   public void init() throws ServletException {
	        String jdbcDriverName = getInitParameter("jdbcDriver");
	        String jdbcURL = getInitParameter("jdbcURL");

	        try {
	            ConnectionPool cp = new ConnectionPool(jdbcDriverName, jdbcURL);
	            userDAO = new UserDAO(cp,"vinbasek_user");
	            fDAO = new FavoriteDAO(cp,"vinbasek_favorite");
	            
	        } catch (DAOException e) {
	            throw new ServletException(e);
	        }
	    }
	   
	   
	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        if (session.getAttribute("user") == null) {
	            login(request, response);
	        } else {
	        	
	            if(request.getParameter("para")!=null){
	            String fid = request.getParameter("para");
	            int fd =Integer.parseInt(fid);
	            try {
					Favorite[] array1 = fDAO.match(MatchArg.equals("favoriteId",fd));
					for(Favorite ar:array1){
						
						   int count = ar.getClickCount();
						   count+=1;
						   ar.setClickCount(count);
						   ar.setFavoriteId(fd);
			               fDAO.update(ar);
			        }
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	                        		    
	            }
	           manageList(request, response);
	        	
	        }
	    }
	   public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	   
	   private void login(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException{
		   LoginForm form = new LoginForm(request);
		   int uid;
		   List<String> error1 = new ArrayList<String>();
		   RegisterationF frm = new RegisterationF(request);
		   User user;
		   if(form.isPresent()==false){
			   outputLoginPage(response,form,null);			   
		   }
		   else if(form.getButton().equals("Login")){
			   if(form.getValidationErrors()!=null){
				   error1.addAll(form.getValidationErrors()); 
				   if(error1.size()!=0){
					   outputLoginPage(response,form,error1);
					   return;
					   } 
				    }
			   try{
			   	   User[] array = userDAO.match(MatchArg.equals("emailAddress", form.getEmailAddress()));   
	       		       for(User ar:array){
					   if(ar.getPassword()!=null && ar.getPassword().equals(form.getPassword()) ){
						 //  error1.add("logged in");
		                   // outputLoginPage(response, form, error1);
						   
		                //    manageList(request, response);
		                    uid=ar.getUserId();
		                    user = userDAO.read(uid);
		                    HttpSession session = request.getSession();
		 	                session.setAttribute("user", user);
		 	               manageList(request, response);
		                     return;
						   }
					   else{					   
					   error1.add("Wrong password");
					   outputLoginPage(response, form, error1);
					   return;
					     }	
	       		       }
				   		   		   
	               if (array.length==0) {
	                    error1.add("No such user");
	                    outputLoginPage(response, form, error1);
	                    return;
	               }
	             	               
		 	 }catch(RollbackException e) {
			 error1.add(e.getMessage());
			 outputLoginPage(response,form,error1);		 
			 return;
		 }
	   	   
     }
		   
		   if(form.getButton().equals("Register")){
			    error1.addAll(form.getValidationErrors());
			    if(form.isPresent()==false);{
				   registerPage(response, frm, null);
				   return;
				   }			    
		       }
		         
		   // add the error condition for register
		   
		   else if(frm.getButton().equals("Register1")) {
			   error1.addAll(frm.getValidationErrors());
			   if(error1.size()!=0){
			     
			     System.out.println("in the if");
		    	registerPage(response, frm, error1);
					   return;
		            
	
			   }else{
				   System.out.println("iin the else");
			    	try{
			    		User[] array3 = userDAO.match(MatchArg.equals("emailAddress", form.getEmailAddress())); 
			    		if(array3.length==0){
				    	   user = new User();
			               user.setEmailAddress(frm.getEmailAddress());
			               user.setPassword(frm.getPassword());
			               user.setFirstName(frm.getFirstName());
			               user.setLastName(frm.getLastName());
			               userDAO.create(user);
			               HttpSession session = request.getSession();
			               session.setAttribute("user", user);
			               manageList(request, response);
			               return;
			    		}else{
			    			error1.add("User already exists!");
			    			registerPage(response, frm, error1);
			    			return;
			    		}
				    	   }catch(RollbackException e){
				    		   error1.add(e.getMessage());
				    		   registerPage(response, frm, error1);
				    	   }
			      }
			    	
			   }
			   
		   }
			      
			    	   
		              
           
		            
		        	            
	   	   private void manageList(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
		   
		   String action = request.getParameter("action");
		   if (action == null) {
	            // No change to list requested
	            outputList(request,response);
	            return;
	        }
		   if (action.equals("Add")) {
	            processAdd(request, response);
	            return;
	        }
		   if(action.equals("logout")){
			   HttpSession session = request.getSession();
               session.setAttribute("user", null);
               LoginForm form1 = new LoginForm(request);
               outputLoginPage(response,form1,null);
		   }
		  outputList1(request,response, "No such operation: " + action);
	   }
			   
	   private void processAdd(HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {
		   List<String> errors = new ArrayList<String>();

	        UrlForm form = new UrlForm(request);

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	          outputList(request,response, errors);
	            return;
	        }
	        try {
	        	Favorite fBean = new Favorite();
	        	fBean.setURL(form.getUrl());
	        	fBean.setComment(form.getComment());
	        	User u = (User) request.getSession().getAttribute("user");
	        	int uid=u.getUserId();
	        	fBean.setUserId(uid);
	        	fBean.setClickCount(0);
	            fDAO.create(fBean);
	            outputList1(request,response, "Item Added");
	        } catch (RollbackException e) {
	            errors.add(e.getMessage());
	            outputList(request,response, errors);
	        }
	    }      

	   private void generateHead(PrintWriter out) {
	        out.println("  <head>");
	        out.println("    <meta charset=\"utf-8\"/>");
	        out.println("    <style> p.inset {border-bottom-style: inset;}");
	        out.println("    table{border:2px solid black;");
	        out.println("    background-color: #FFFFCC;}");
	        out.println("    td {");
	        out.println("    text-align:center;");
	        out.println("    width: 50%;}");
	        out.println("    th,");
	        out.println("    td{");
	        out.println("    text-align:left;} </style>");
	        out.println("    <title>Favorite WebPage List</title>");
	        out.println("  </head>");
	    }
	   
	   private void outputLoginPage(HttpServletResponse response, LoginForm form,
	            List<String> errors) throws IOException {
	        response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        out.println("<!DOCTYPE html>");
	        out.println("<html>");

	        generateHead(out);

	        out.println("<body>");
	        out.println("<h2>Favorite WebPage List</h2>");

	        if (errors != null && errors.size() > 0) {
	            for (String error : errors) {
	                out.println("<p style=\"font-size: large; color: red\">");
	                out.println(error);
	                out.println("</p>");
	            }
	        }

	        // Generate an HTML <form> to get data from the user
	        out.println("<form method=\"POST\">");
	        out.println("    <table>");
	        out.println("        <tr>");
	        out.println("            <td style=\"font-size: x-large\">E-mail Address:</td>");
	        out.println("            <td>");
	        out.println("                <input type=\"text\" name=\"email\"");
	        if (form != null && form.getEmailAddress() != null) {
	            out.println("                    value=\"" + form.getEmailAddress()+ "\"");
	        }
	        out.println("                />");
	        out.println("            </td>");
	        out.println("        </tr>");
	        out.println("        <tr>");
	        out.println("            <td style=\"font-size: x-large\">Password:</td>");
	        out.println("            <td><input type=\"password\" name=\"password\" /></td>");
	        out.println("        </tr>");
	        out.println("        <tr>");
	        out.println("            <td colspan=\"2\" style=\"text-align: center;\">");
	        out.println("                <input type=\"submit\" name=\"button\" value=\"Login\" />");
	        out.println("                <input type=\"submit\" name=\"button\" value=\"Register\" />");
	        out.println("            </td>");
	        out.println("        </tr>");
	        out.println("    </table>");
	        out.println("</form>");
	        out.println("</body>");
	        out.println("</html>");
	    }
	   
	   private void registerPage(HttpServletResponse response, RegisterationF form,
	            List<String> errors) throws IOException {
	        response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();

	        out.println("<!DOCTYPE html>");
	        out.println("<html>");

	        generateHead(out);

	        out.println("<body>");
	        out.println("<h2>Favorite WebPage List</h2>");

	        if (errors != null && errors.size() > 0) {
	            for (String error : errors) {
	                out.println("<p style=\"font-size: large; color: red\">");
	                out.println(error);
	                out.println("</p>");
	            }
	        }

	        // Generate an HTML <form> to get data from the user
	        out.println("<form method=\"POST\">");
	        out.println("    <table>");
	        out.println("        <tr>");
	        out.println("            <td style=\"font-size: x-large\">E-mail Address:</td>");
	        out.println("            <td>");
	        out.println("                <input type=\"text\" name=\"email\"");
	        if (form != null && form.getEmailAddress() != null) {
	            out.println("                    value=\"" + form.getEmailAddress()+ "\"");
	        }
	        out.println("                />");
	        out.println("            </td>");
	        out.println("            </td>");
	        out.println("        </tr>");
	        out.println("        <tr>");
	        out.println("            <td style=\"font-size: x-large\">First Name:</td>");
	        out.println("            <td>");
	        out.println("                <input type=\"text\" name=\"firstName\"");
	        if (form != null && form.getFirstName() != null) {
	            out.println("                    value=\"" + form.getFirstName()+ "\"");
	        }
	        out.println("                />");
	        out.println("            </td>");
	        out.println("            </td>");
	        out.println("        </tr>");
	        out.println("        <tr>");
	        out.println("            <td style=\"font-size: x-large\">Last Name:</td>");
	        out.println("            <td>");
	        out.println("                <input type=\"text\" name=\"lastName\"");
	        if (form != null && form.getLastName() != null) {
	            out.println("                    value=\"" + form.getLastName()+ "\"");
	        }
	        out.println("                />");
	        out.println("            </td>");
	        out.println("            </td>");
	        out.println("        </tr>");
	        out.println("        <tr>");
	        out.println("            <td style=\"font-size: x-large\">Password:</td>");
	        out.println("            <td><input type=\"password\" name=\"password\" /></td>");
	        out.println("        </tr>");
	        out.println("        <tr>");
	        out.println("            <td colspan=\"2\" style=\"text-align: center;\">");
	        out.println("                <input type=\"submit\" name=\"button\" value=\"Register1\" />");
	        out.println("            </td>");
	        out.println("        </tr>");
	        out.println("    </table>");
	        out.println("</form>");
	        out.println("</body>");
	        out.println("</html>");
	    }
	   
	   private void outputList(HttpServletRequest request,HttpServletResponse response)
	            throws IOException {
	        // Just call the version that takes a List passing an empty List
	        List<String> list = new ArrayList<String>();
	        outputList(request,response, list);
	    }

	    private void outputList1(HttpServletRequest request,HttpServletResponse response, String message)
	            throws IOException {
	        // Just put the message into a List and call the version that takes a
	        // List
	        List<String> list = new ArrayList<String>();
	        list.add(message);
	        outputList(request,response, list);
	    }
	    
	    private void outputList(HttpServletRequest request,HttpServletResponse response,
	            List<String> messages) throws IOException {
	    	
	    	Favorite[] fb;
	    	try{
	    		User u = (User) request.getSession().getAttribute("user");
	    		int uid = u.getUserId();
	    		
	    		fb=fDAO.getUserFavorites(uid);
	    	 
	    	}catch (RollbackException e) {
	            // If there's an access error, add the message to our list of
	            // messages
	            messages.add(e.getMessage());
	            fb = new Favorite[0];
	        } 
	    	
	    	User u = (User) request.getSession().getAttribute("user");
    		
    		String Fname= u.getFirstName();
    		String Lname= u.getLastName();
	    response.setContentType("text/html");
	    
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");

        generateHead(out);

        out.println("<body>");
        out.println("<h2>Favorites for "+Fname+"  "+ Lname +" </h2>");

        // Generate an HTML <form> to get data from the user
        out.println("<form method=\"POST\">");
        
        out.println("    <table>");
        out.println("        <tr><td colspan=\"3\"><hr/></td></tr>");
        out.println("        <tr>");
        out.println("            <td style=\"font-size: large\">URL:</td>");
        out.println("            <td colspan=\"2\"><input type=\"text\" size=\"40\" name=\"Url\"/></td>");
        out.println("        </tr>");
        out.println("            <td style=\"font-size: large\">Comment:</td>");
        out.println("            <td colspan=\"2\"><input type=\"text\" size=\"40\" name=\"comment\"/></td>");
        out.println("        </tr>");
        out.println("        <tr>");
        out.println("            <td></td>");
        out.println("            <td><input type=\"submit\" name=\"action\" value=\"Add\"/></td>");
        out.println("            <input type=\"submit\" name=\"action\" value=\"logout\"/>");
        out.println("        </tr>");
        out.println("        <tr><td colspan=\"3\"><hr/></td></tr>");
        out.println("    </table>");
        out.println("</form>");

        for (String message : messages) {
            out.println("<p style=\"font-size: large; color: red\">");
            out.println(message);
            out.println("</p>");
        }
        out.println("<p style=\"font-size: x-large\">Your Favorite list now has "
                + fb.length + " Urls.</p>");
        out.println("<table>");
        for (int i = 0; i < fb.length; i++) {
            // List output 
            out.println("    <tr>");
     
            out.println("        <td><span style=\"font-size: x-large\"><a href=\"/Hw3?para="+ fb[i].getFavoriteId()+"\">"+ fb[i].getURL() + "</a></span> </td>");
           
                          
            
            out.println("    </tr>");
            out.println("    <tr>");
            out.println("        <td><span style=\"font-size: x-large\">"
                    + fb[i].getComment() + "</span> </td>");
            out.println("    </tr>");
            out.println("    <tr>");
            out.println("        <td><span style=\"font-size: x-large\">"
                    + fb[i].getClickCount() + " Clicks</span> </td>");
            out.println("    </tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
	    





	   





