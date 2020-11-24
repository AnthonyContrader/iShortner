package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import it.contrader.dto.ServerDTO;
import it.contrader.dto.UrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.ServerService;
import it.contrader.service.ShortUrlService;
import it.contrader.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private ShortUrlService shortUrlService;
	@Autowired
	private ServerService serverService;

	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO dto) {
		return service.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
	}

//	@GetMapping("/getall")
//	public String getAll(HttpServletRequest request) {
//		request.getSession().setAttribute("chkUser", 0);
//		setAll(request);	
//		return "users";
//	}

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
		List<UrlDTO> urlList = new ArrayList<>();
		urlList = (List<UrlDTO>) shortUrlService.readList(id);
		request.getSession().setAttribute("urlDto", urlList);
		List<ServerDTO> serverList = new ArrayList<>();
		if(urlList.size() >= 1) {
			serverList = serverService.searchList(urlList.get(0).getFk_url());
		}
		request.getSession().setAttribute("server", serverList);
		return "readuser";
	}

	@PostMapping("/edituser")
	public String edit(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("username") String username, @RequestParam("password") String password) {
		UserDTO userDto = new UserDTO();
		userDto.setId(id);
		userDto.setUsername(username);
		userDto.setPassword(password);
		Usertype usertype = null;
		userDto.setUsertype(usertype.USER);
		service.update(userDto);
		request.getSession().setAttribute("user", userDto);
		
		return "edituser";
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
