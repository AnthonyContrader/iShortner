package it.contrader.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.UrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ShortUrlService;

public class Test extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		
		//if(request != null) {
			String url = request.getParameter("url").toString();
			UrlDTO urlDto = new UrlDTO();
			urlDto.setUrl(url);
			UserDTO user = (UserDTO) session.getAttribute("user");

			urlDto = ShortUrlService.createShortUrl(user, urlDto);
			
			request.setAttribute("url", urlDto);
			System.out.println(urlDto.getShortUrl());
			getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
		//}
	}
}
