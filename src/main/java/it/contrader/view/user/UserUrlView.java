package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class UserUrlView extends AbstractView {
	
	private String url;
	Request r;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		r = request;
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci url: ");
		url = this.getInput();
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		r.put("url", url);
		MainDispatcher.getInstance().callAction("ShortUrl", "doControl", r);
	}

	
}
