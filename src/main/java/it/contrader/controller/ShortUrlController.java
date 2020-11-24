package it.contrader.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.UrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;
import it.contrader.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class ShortUrlController {

	@Autowired
	private ShortUrlService urlService;
	@Autowired
	private ServerService serverService;

	@PostMapping("/create")
	public UrlDTO createUrl(@RequestBody UrlDTO url) throws MalformedURLException {

		url = urlService.createShortUrl(url);
		if(url == null) {
			return null;
		}
		//serverService.generator(url);
		return url;
	}

	@PostMapping("/redirect")
	public void redirectUrl(HttpServletRequest request, @RequestParam(value = "shortUrl", required = true) String shortUrl, HttpServletResponse response) {	
		UrlDTO url = new UrlDTO();
		url.setShorturl(shortUrl);
		url = urlService.getLongUrl(url);
		String fullUrl = url.getLongurl();	
		response.setHeader("Location", fullUrl);
		response.setStatus(302);	
	}

	@GetMapping("/readurl")
	public String readUrl(HttpServletRequest request) {	
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		Long id = dto.getId();
		List<UrlDTO> urlList = new ArrayList<>();
		urlList = (List<UrlDTO>) urlService.readList(id);
		request.getSession().setAttribute("urlDto", urlList);
		return "readurl";
	}

}
