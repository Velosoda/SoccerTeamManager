package com.manager.MainAppMain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletMUT
 */
@WebServlet("/MUT")
public class ServletMUT extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ServletMUT() {
        super();
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Market market = (Market)getServletContext().getAttribute("market");
		League league = (League)getServletContext().getAttribute("league");
		ModelLoading ml = (ModelLoading) getServletContext().getAttribute("ModelLoading");
		
		request.setAttribute("totalMarket", ml.totalMarket);
		request.setAttribute("league", league);
		request.setAttribute("market", market);
		request.getRequestDispatcher("JSP/mut.jsp").forward(request, response);
	}

}
