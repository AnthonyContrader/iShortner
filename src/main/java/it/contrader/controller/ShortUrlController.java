package it.contrader.controller;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ShortUrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;

@Controller
@RequestMapping("/user")
public class ShortUrlController {

	@Autowired
	private ShortUrlService service;
	@Autowired
	private ServerService serv;

	@PostMapping("/create")
	public String createUrl(HttpServletRequest request, @RequestParam(value = "url", required = true) String longUrl) throws MalformedURLException {
		
		UserDTO user = new UserDTO();
		user = (UserDTO) request.getSession().getAttribute("user");
		ShortUrlDTO url = new ShortUrlDTO();
		url.setLongurl(longUrl);
		url = service.createShortUrl(user, url);
		serv.generator(url);
		// messaggio di errore per la view
		if(url.getId() == null && url.getLongurl() == null && url.getShorturl() == null && url.getFk_url() == null) {
			url.setShorturl("err");
			request.getSession().setAttribute("shortUrl", url);
			return "homeuser";
		}

		request.getSession().setAttribute("shortUrl", url);
		return "homeuser";
	}
	
	@PostMapping("/redirect")
	public void redirectUrl(HttpServletRequest request, @RequestParam(value = "shortUrl", required = true) String shortUrl, HttpServletResponse response) {	
		ShortUrlDTO url = new ShortUrlDTO();
		url.setShorturl(shortUrl);
		url = service.getLongUrl(url);
		String fullUrl = url.getLongurl();	
		response.setHeader("Location", fullUrl);
		response.setStatus(302);	
	}
	
}
