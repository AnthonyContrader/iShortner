package it.contrader.controller;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

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
	private ServerService serverService;

	@PostMapping("/create")
	public String createUrl(HttpServletRequest request, @RequestParam(value = "url", required = true) String longUrl) throws MalformedURLException {
		
		UserDTO user = new UserDTO();
		user = (UserDTO) request.getSession().getAttribute("user");
		ShortUrlDTO	url = new ShortUrlDTO();
		url.setLongurl(longUrl);
		url = service.createShortUrl(user, url);
		//request.getSession().setAttribute("shortUrl", url);
		
		serverService.generator(url);
		
		return "homeuser";
	}
}
