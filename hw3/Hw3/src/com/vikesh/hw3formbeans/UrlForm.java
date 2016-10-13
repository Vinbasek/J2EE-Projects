// Author :Vikesh Inbasekharan
// Date : 30 - 09 - 2016
// course : 08-672

package com.vikesh.hw3formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class UrlForm {

	private String Url;
	private String comment;

	public String getUrl() {
        return Url;
    }
    public String getComment() {
		return comment;
	}
	
	public UrlForm(HttpServletRequest request) {
        Url = sanitize(request.getParameter("Url"));
        comment = sanitize(request.getParameter("comment"));
    }

    

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (Url == null || Url.length() == 0) {
            errors.add("Url is required");
        }
        if (comment == null || comment.length() == 0) {
            errors.add("Comment is required");
        }
        

        return errors;
    }

    private String sanitize(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }

}
