package it.contrader.controller;

import it.contrader.main.MainDispatcher;
import it.contrader.model.User;
import it.contrader.service.ShortUrlService;
import it.contrader.view.AbstractView;

public class ShortUrlController extends AbstractView implements Controller{
	
	private ShortUrlService shortServ;
	
	public void doControl(Request request) {
		System.out.println("Inserisci url: ");
		String url = this.getInput();	
		String username = request.get("username").toString();
		request.put("shortUrl", shortServ.createShortUrl(username, url));
		MainDispatcher.getInstance().callView("HomeUser", request);
	}

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}

}
