package it.contrader.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ShortUrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;
import it.contrader.service.UserService;

@Controller
@RequestMapping("/user")
public class ShortUrlController {

	@Autowired
	private ShortUrlService serviceUrl;

	@Autowired
	private ServerService serviceServer;

	@PostMapping("/create")
	public String createUrl(HttpServletRequest request, @RequestParam(value = "url", required = true) String longUrl) throws MalformedURLException {
		
		UserDTO user = new UserDTO();
		user = (UserDTO) request.getSession().getAttribute("user");
		ShortUrlDTO url = new ShortUrlDTO();
		url.setLongurl(longUrl);
		url = serviceUrl.createShortUrl(user, url);
		serviceServer.generator(url);
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
		url = serviceUrl.getLongUrl(url);
		String fullUrl = url.getLongurl();	
		response.setHeader("Location", fullUrl);
		response.setStatus(302);	
	}

	@GetMapping("/readurl")
	public String readUrl(HttpServletRequest request) {
		System.out.println("ci arrivo?");
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		Long id = dto.getId();
		List<ShortUrlDTO> urlList = new ArrayList<>();
		urlList = (List<ShortUrlDTO>) serviceUrl.readList(id);
		request.getSession().setAttribute("urlDto", urlList);
		return "readurl";
	}

}
