package com.manager.MainAppMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserSetup")
public class ServletInitialUserSetup extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ServletInitialUserSetup() 
    {
        super();
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//load in all context vars
		Market market = (Market)getServletContext().getAttribute("market");
		League league = (League)getServletContext().getAttribute("league");
		ModelLoading ml = (ModelLoading) getServletContext().getAttribute("ModelLoading");
		
		//get values from response
		
	}

}
