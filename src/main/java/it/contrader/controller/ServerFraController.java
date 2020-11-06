package it.contrader.controller;

import java.util.List;

import it.contrader.dto.ServerFraDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ServerFraService;

public class ServerFraController implements Controller{
	
private static String sub_package = "server.";
	
	private ServerFraService serverService;
	
	public ServerFraController() {
		this.serverService = new ServerFraService();
	}
	
	
	@Override
	public void doControl(Request request) {
		
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		int id;
		String nome_citta;
		int fk_id_url;

		switch (mode) {
		
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			ServerFraDTO serverDTO = serverService.read(id);
			request.put("server", serverDTO);
			MainDispatcher.getInstance().callView(sub_package + "ServerRead", request);
			break;
		
		/*case "INSERT":
			username = request.get("username").toString();
			password = request.get("password").toString();
			usertype = request.get("usertype").toString();
			
			//costruisce l'oggetto user da inserire
			UserDTO usertoinsert = new UserDTO(username, password, usertype);
			//invoca il service
			userService.insert(usertoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "UserInsert", request);
			break;*/
		
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			//Qui chiama il service
			serverService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "ServerRead", request);
			break;
		
		// Arriva qui dalla UserUpdateView
		/*case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			username = request.get("username").toString();
			password = request.get("password").toString();
			usertype = request.get("usertype").toString();
			UserDTO usertoupdate = new UserDTO(username, password, usertype);
			usertoupdate.setId(id);
			userService.update(usertoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "UserUpdate", request);
			break;*/
			
		case "SERVERLIST":
			List<ServerFraDTO> serversDTO = serverService.getAll();
			request.put("servers", serversDTO);
			MainDispatcher.getInstance().callView("Server", request);
			break;
			
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "ServerRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "ServerRead", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "ServerRead", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "ServerRead", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeAdmin", null);
				break;
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
			
		default:
			MainDispatcher.getInstance().callView("Login", null);
		}
	}

}
