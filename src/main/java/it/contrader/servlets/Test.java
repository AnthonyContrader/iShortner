package it.contrader.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dao.UrlDAO;
import it.contrader.dto.UrlTableDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;

public class Test extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@SuppressWarnings("static-access")
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		//TODO finire servlet in modo che rimandi al dao e legga longUrl
		if(request != null) {
			String urlShort = request.getParameter("keywords");
			UrlTableDTO urlDTO = new UrlTableDTO();
			urlDTO.setShortUrl(urlShort);
			String urlOriginale = UrlDAO.findLongUrl(urlDTO);
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", urlOriginale);
		}
	
	}
}
