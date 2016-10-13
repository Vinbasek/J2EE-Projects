package com.vikesh.calculator;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		outputHtml(request, response, null,null);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String Caction = request.getParameter("action");
    	String Cvalue1 = request.getParameter("Num1");
    	String Cvalue2 = request.getParameter("Num2");
    	String Msg1="";
    	String Msg2="";
    	
    	    	
    	if(Cvalue1==null){Msg1="Missing parameter!!";}
    	
    	else if(Cvalue2==null){Msg1="Missing parameter!!";}
    	else{
    	    	
    	if(Caction==null){
    		Msg1="Missing parameter!!";
    }else if(Caction.equals("+")){
   	    String t1 = request.getParameter("Num1");
   	    String t2 = request.getParameter("Num2");
    	
    	  if(t1=="" && t2=="" ){
    		Msg1="X is blank!, Enter a number";
    		Msg2="Y is blank!, Enter a number";
    		}else if(t1==""){Msg1="X is blank!, Enter a number";
    		
    		}else if(t2==""){Msg2="Y is blank!, Enter a number";} 
    		else {
        		
    	try{
    		float i1=Float.parseFloat(request.getParameter("Num1"));
    		try{
        		float i2=Float.parseFloat(request.getParameter("Num2"));
        		Msg1=Add(request);
        		}catch(NumberFormatException e){
        		Msg2="Y is not a number! Try again with a numeric input";
        		Msg1=" ";
        	   }
    		  }catch(NumberFormatException e){
    			  Msg1="X is not a number! Try again with a numeric input";
    		Msg2="";
    		try{
        		float i2=Float.parseFloat(request.getParameter("Num2"));
    		}catch(NumberFormatException f){
    			Msg2="Y is not a number! Try again with a numeric input"; 
    			Msg1="X is not a number! Try again with a numeric input";
    			}
    	   }
      	}
    	//  Msg2="X is blank!"; 
    	
        }else if(Caction.equals("-")){
        	String t1 = request.getParameter("Num1");
       	    String t2 = request.getParameter("Num2");
        	
        	  if(t1=="" && t2=="" ){
        		Msg1="X is blank!, Enter a number";
        		Msg2="Y is blank!, Enter a number";
        		}else if(t1==""){Msg1="X is blank!, Enter a number";
        		
        		}else if(t2==""){Msg2="Y is blank!, Enter a number";} 
        		else {
        	try{
        		float i1=Float.parseFloat(request.getParameter("Num1"));
        		try{
            		float i2=Float.parseFloat(request.getParameter("Num2"));
            		Msg1=Subtract(request);
            		}catch(NumberFormatException e){
            		Msg2="Y is not a number! Try again with a numeric input";
            		Msg1=" ";
            	   }
        		  }catch(NumberFormatException e){
        			  Msg1="X is not a number! Try again with a numeric input";
        		Msg2="";
        		try{
            		float i2=Float.parseFloat(request.getParameter("Num2"));
        		}catch(NumberFormatException f){
        			Msg2="Y is not a number! Try again with a numeric input"; 
        			Msg1="X is not a number! Try again with a numeric input";
        			}
        	   }
        }
        }
    	
    	else if(Caction.equals("*")){
    		String t1 = request.getParameter("Num1");
       	    String t2 = request.getParameter("Num2");
        	
        	  if(t1=="" && t2=="" ){
        		Msg1="X is blank!, Enter a number";
        		Msg2="Y is blank!, Enter a number";
        		}else if(t1==""){Msg1="X is blank!, Enter a number";
        		
        		}else if(t2==""){Msg2="Y is blank!, Enter a number";} 
        		else {
    		try{
        		float i1=Float.parseFloat(request.getParameter("Num1"));
        		try{
            		float i2=Float.parseFloat(request.getParameter("Num2"));
            		Msg1=Multiply(request);
            		}catch(NumberFormatException e){
            		Msg2="Y is not a number! Try again with a numeric input";
            		Msg1=" ";
            	   }
        		  }catch(NumberFormatException e){
        			  Msg1="X is not a number! Try again with a numeric input";
        		Msg2="";
        		try{
            		float i2=Float.parseFloat(request.getParameter("Num2"));
        		}catch(NumberFormatException f){
        			Msg2="Y is not a number! Try again with a numeric input"; 
        			Msg1="X is not a number! Try again with a numeric input";
        			}
        	   }	
    	}
    }
 	else if(Caction.equals("/")){
 		String t1 = request.getParameter("Num1");
   	    String t2 = request.getParameter("Num2");
    	
    	  if(t1=="" && t2=="" ){
    		Msg1="X is blank!, Enter a number";
    		Msg2="Y is blank!, Enter a number";
    		}else if(t1==""){Msg1="X is blank!, Enter a number";
    		
    		}else if(t2==""){Msg2="Y is blank!, Enter a number";} 
    		else {
 		try{
    		float i1=Float.parseFloat(request.getParameter("Num1"));
    		try{
        		float i2=Float.parseFloat(request.getParameter("Num2"));
        		Msg1=Divide(request);
        		}catch(NumberFormatException e){
        		Msg2="Y is not a number! Try again with a numeric input";
        		Msg1=" ";
        	   }
    		  }catch(NumberFormatException e){
    			  Msg1="X is not a number! Try again with a numeric input";
    		Msg2="";
    		try{
        		float i2=Float.parseFloat(request.getParameter("Num2"));
    		}catch(NumberFormatException f){
    			Msg2="Y is not a number! Try again with a numeric input"; 
    			Msg1="X is not a number! Try again with a numeric input";
    			}
    	   }	
 	}
    	
  }
}
 outputHtml(request, response, Msg1,Msg2);
     
     
    }
    
    private String Add(HttpServletRequest request){
    	float n1=Float.parseFloat(request.getParameter("Num1"));
    	float n2=Float.parseFloat(request.getParameter("Num2"));
    	float Ans=n1+n2;
    	NumberFormat Aform = NumberFormat.getNumberInstance(Locale.US);
    	DecimalFormat f =(DecimalFormat) Aform;
    	f.applyPattern("#.00");
    	f.setGroupingUsed(true);
    	f.setGroupingSize(3);
    	
    	String M = "The Sum of "+f.format(n1)+ " and "+f.format(n2)+ " is: "+ f.format(Ans);
    	return M;    	  	
    }
    
    private String Subtract(HttpServletRequest request){
    	float n1=Float.parseFloat(request.getParameter("Num1"));
    	float n2=Float.parseFloat(request.getParameter("Num2"));
    	NumberFormat Aform = NumberFormat.getNumberInstance(Locale.US);
    	DecimalFormat f =(DecimalFormat) Aform;
    	f.applyPattern("#.00");
    	f.setGroupingUsed(true);
    	f.setGroupingSize(3);
    	float Ans=n1-n2;
    	
    	String M = "The Difference of "+f.format(n1)+ " and "+f.format(n1)+ " is: "+f.format(Ans);
    	return M;    	  	
    }
    
    private String Multiply(HttpServletRequest request){
    	float n1=Float.parseFloat(request.getParameter("Num1"));
    	float n2=Float.parseFloat(request.getParameter("Num2"));
    	NumberFormat Aform = NumberFormat.getNumberInstance(Locale.US);
    	DecimalFormat f =(DecimalFormat) Aform;
    	f.applyPattern("#.00");
    	f.setGroupingUsed(true);
    	f.setGroupingSize(3);
    	float Ans=n1*n2;
    	String M = "The Product of "+f.format(n1)+ " and "+f.format(n2)+ " is: "+f.format(Ans);
    	return M;    	  	
    }
    
    private String Divide(HttpServletRequest request){
    	float n1=Float.parseFloat(request.getParameter("Num1"));
    	float n2=Float.parseFloat(request.getParameter("Num2"));
    	String Ms;
    	NumberFormat Aform = NumberFormat.getNumberInstance(Locale.US);
    	DecimalFormat f =(DecimalFormat) Aform;
    	f.applyPattern("#.00");
    	f.setGroupingUsed(true);
    	f.setGroupingSize(3);
    		if(n2!=0){
    	    float Ans=n1/n2;
    	    Ms = "The Quotient of "+f.format(n1)+ " and "+f.format(n2)+ " is: "+f.format(Ans);
    		}
    		else{
    		Ms ="Can not divide by zero!";	
     		}
     	    return Ms;	  	
    }
    
    
    private void outputHtml(HttpServletRequest request, HttpServletResponse response, String message1,String message2)
			throws IOException {
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String op1 =request.getParameter("Num1");
        if(op1==null){op1="0";}
        String op2 =request.getParameter("Num2");
        if(op2==null){op2="0";}
       // if(op1==null&&op2==null){op1="0";op2="0";}
        else if(op1==""&&op2==""){op1="0";op2="0";}
        else if(op1==""){op1="0";}
        else if(op2==""){op2="0";}
        out.println("<!doctype html>");
        out.println("<html>");
        // Generate the HTML <head>
        out.println("<head>");
        out.println("    <title>Calculator</title>");
        out.println("<style>");
        out.println("table{border:2px solid black;");
        out.println("background-color: #FFFFCC;}");
        out.println("</style>");      
        out.println(" </head>");
        out.println("<body>");
        out.println("<h2>Calculator</h2>");
        // Generate an HTML <form> to get data from the user
        out.println("<form method=\"POST\">");
        out.println("  <table>");
        //out.println("  <tr><td colspan=\"3\"><hr/></td></tr>");
        out.println("  <tr>");
        out.println("    <td>X:</td>");
        out.println("    <td colspan=\"2\"><input type=\"text\" value=" +op1+" size=\"40\" name=\"Num1\"/></td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <td>Y:</td>");
        out.println("    <td colspan=\"2\"><input type=\"text\"value=" +op2+" size=\"40\" name=\"Num2\"/></td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <td colspan=\"2\"><input type=\"submit\" name=\"action\" value=\"+\">");
        out.println("    <input type=\"submit\" name=\"action\" value=\"-\">");
        out.println("    <input type=\"submit\" name=\"action\" value=\"*\">");
        out.println("    <input type=\"submit\" name=\"action\" value=\"/\"></td>");
        out.println("  </tr>");
        out.println("  </table>");
        out.println("</form>");

        if (message1 != null) {
        		out.println("<p style=\"color:red\">");
        		out.println(message1);
        		out.println("<br>");
        		out.println(message2);
        		out.println("</p>");
        }
        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }

}

