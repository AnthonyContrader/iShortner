
package it.contrader.view.user;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.ServerFraDTO;
import it.contrader.dto.UrlTableDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserReadView extends AbstractView {

	private int id;
	private Request request;
	private final String mode = "READ";

	public UserReadView() {
	}

	/**
	 * Se la request � null (ovvero quando arriva dal controller con mode GETCHOICE e choice L 
	 * il metodo � vuoto.
	 * 
	 * Altrimenti se arriva con uno user nella request (ovvero quando arriva
	 * dal controller con mode READ) mostra lo user. In questo caso torna alla UserView senza eseguire
	 * gli altri due metodi
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			UserDTO user = (UserDTO) request.get("user");
			System.out.println("\n");
			@SuppressWarnings("unchecked")
			List<ServerFraDTO> serverList = (List<ServerFraDTO>) request.get("serverList");
			@SuppressWarnings("unchecked")
			List<UrlTableDTO> urlTableDto = (List<UrlTableDTO>) request.get("urltable");
			String u = "";
			String s = "";
			String h = user.getUsername() + "\t" + "Url\t\tPosizione";
			System.out.println("\tLista url\n----------------------------");
			System.out.println(h);
			for(int i=0; i<urlTableDto.size(); i++) {
				u = urlTableDto.get(i).getId() + "\t" + urlTableDto.get(i).getUrl() + "\t";
				s = serverList.get(i).getNomeCitta();
				System.out.println(u+s);
			}

			MainDispatcher.getInstance().callView("User", null);
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'utente:");
		id = Integer.parseInt(getInput());
	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("User", "doControl", request);
	}

}
