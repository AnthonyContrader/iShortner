package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.ServerDTO;
import it.contrader.dto.UrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.StatsUrl;
import it.contrader.model.User.Usertype;
import it.contrader.service.ServerService;
import it.contrader.service.UrlService;
import it.contrader.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
//specificare l'azione di mapping con deletemapping, postmapping, getmapping, putmapping
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private UrlService shortUrlService;
	@Autowired
	private ServerService servService;
	
	
	@PostMapping("/login")
	public UserDTO login(@RequestBody UserDTO dto) {
		return service.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
	}

	@GetMapping("/getall")
	public List<UserDTO> getAll() {	
		return service.getAll();
	}
	
	@DeleteMapping("/delete")
	public boolean delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		return true;
	}

//	@GetMapping("/preupdate")
//	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
//		request.getSession().setAttribute("dto", service.read(id));
//		return "updateuser";
//	}

	@PutMapping("/update")
	public UserDTO update(@RequestBody UserDTO dto) {
		return service.update(dto);
	}
	
	//manca controllo registrazione utente
	@PostMapping("/register")
	public UserDTO register(@RequestBody UserDTO dto) {
		return service.insert(dto);	
	}

	@PostMapping("/insert")
	public UserDTO insert(@RequestBody UserDTO dto) {
		boolean chkUser = service.chkUser(dto.getUsername());
		if(!chkUser) {
			dto = service.insert(dto);
			return dto;  
		}
		dto = null;
		return dto;
	}

	@GetMapping("/readinfourl")
	public List<UrlDTO> readurl(HttpServletRequest request, @RequestParam("id") Long id) {
		List<UrlDTO> urlList = new ArrayList<>();
		urlList = (List<UrlDTO>) shortUrlService.readList(id);
		if(urlList.isEmpty()) {
			return null;
		}
		return urlList;
	}
	
	@GetMapping("/readserver")
	public List<ServerDTO> readserver(HttpServletRequest request, @RequestParam("id") Long id) {
		List<ServerDTO> serverList = new ArrayList<>();
		serverList = servService.searchList(id);
		return serverList;
	}
	
	@GetMapping("/getcount")
	public List<StatsUrl> readStats(){
		return shortUrlService.getCount();
	}
	
	@PostMapping("/edituser")
	public String edit(HttpServletRequest request,  @RequestParam("id") Long id, @RequestParam("username") String username, @RequestParam("password") String password) {
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

//	private void setAll(HttpServletRequest request) {
//		request.getSession().setAttribute("list", service.getAll());
//	}
}
