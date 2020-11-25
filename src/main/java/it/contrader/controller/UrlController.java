package it.contrader.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.UrlDTO;
import it.contrader.service.ServerService;
import it.contrader.service.UrlService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {

	@Autowired
	private UrlService urlService;
	@Autowired
	private ServerService serverService;

	@PostMapping("/create")
	public UrlDTO createUrl(@RequestBody UrlDTO url) throws MalformedURLException {

		url = urlService.createShortUrl(url);
		if(url == null) {
			return null;
		}
		serverService.generator(url);
		return url;
	}

	@PostMapping("/redirect")
	public UrlDTO redirectUrl(@RequestBody UrlDTO url, HttpServletResponse response) {	
		return urlService.getLongUrl(url);
	}

	@GetMapping("/readurl")
	public List<UrlDTO> readUrl(HttpServletRequest request, @RequestParam("id") Long id) {	
		List<UrlDTO> urlList = new ArrayList<>();
		urlList = (List<UrlDTO>) urlService.readList(id);
		if(urlList.isEmpty()) {
			return null;
		}
		return urlList;
	}

}
