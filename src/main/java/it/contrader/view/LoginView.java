package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.model.User;

public class LoginView extends AbstractView {

	private String username;
	
	private String password;

	public void showResults(Request request) {
		if(request != null) {
			System.out.println("Login errato");
		}
	}
	/**
	 * chiede in input all'utente uno username e una password usando il metodo getInput() presente in AbstractView
	 */
	public void showOptions() {
		
		System.out.println("----- .:LOGIN:. ----");
		
		System.out.println(" Nome utente:");
		username = getInput();

		System.out.println(" Password:");
		password = getInput();
	}

	/**
	 * Impacchetta una request (metodo request.put("chiave", valore)) e la manda al controller Home tramite il Dispatcher
	 */
	public void submit() {
		
		Request request = new Request();
		
		request.put("username", username);
		request.put("password", password);
		
		
		User user = new User();
		user.setUsername(username);
		
		MainDispatcher.getInstance().callAction("Home", "doControl", request);
	}


}
