package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.ServerDTO;
import it.contrader.dto.ShortUrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;
import it.contrader.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private ShortUrlService shortUrlService;
	
	@Autowired
	private ServerService servService;
	

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		UserDTO userDTO = service.findByUsernameAndPassword(username, password);
		request.getSession().setAttribute("user", userDTO);
		ShortUrlDTO url = new ShortUrlDTO();
		System.out.println(userDTO.getId());
		url.setShorturl("");
		request.getSession().setAttribute("shortUrl", url);
		request.getSession().setAttribute("chkUser", 0);

		switch (userDTO.getUsertype()) {

		case ADMIN:
			return "homeadmin";

		case USER:
			return "homeuser";

		default:
			return "index";
		}
	}

	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		request.getSession().setAttribute("chkUser", 0);
		setAll(request);	
		return "users";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "users";
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateuser";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.update(dto);
		setAll(request);
		return "users";
	}

	@PostMapping("/register")
	public String register(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
		UserDTO user = new UserDTO();
		user.setUsername(username);
		user.setPassword(password);
		Usertype usertype = null;
		user.setUsertype(usertype.USER);
		service.insert(user);
		return "index";
	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {
		UserDTO dto = new UserDTO();
		boolean chkUser = service.chkUser(username);
		if(!chkUser) {
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setUsertype(usertype);
			service.insert(dto);
			setAll(request);
			request.getSession().setAttribute("chkUser", 2);
		}else {
			setAll(request);
			request.getSession().setAttribute("chkUser", 1);
		}
		return "users";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		UserDTO dto = new UserDTO();
		dto = service.read(id);
		request.getSession().setAttribute("dto", dto);
		List<ShortUrlDTO> urlList = new ArrayList<>();
		urlList = (List<ShortUrlDTO>) shortUrlService.readList(id);
		request.getSession().setAttribute("urlDto", urlList);
		List<ServerDTO> serverList = new ArrayList<>();
		if(urlList.size() == 1) {
			serverList = servService.searchList(urlList.get(0).getFk_url());
		}
		request.getSession().setAttribute("server", serverList);
		return "readuser";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
