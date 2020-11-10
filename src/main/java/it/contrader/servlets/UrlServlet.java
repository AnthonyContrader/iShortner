package it.contrader.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.ServerDTO;
import it.contrader.dto.UrlTableDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;

public class UrlServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		
		if(request != null) {
			String url = request.getParameter("url").toString();
			UrlTableDTO urlDto = new UrlTableDTO();
			urlDto.setLongUrl(url);
			UserDTO user = (UserDTO) session.getAttribute("user");

			urlDto = ShortUrlService.createShortUrl(user, urlDto);
			ServerService.generator(urlDto);
			request.setAttribute("url", urlDto);
			getServletContext().getRequestDispatcher("/homeuser.jsp").forward(request, response);
		}
	}
}
