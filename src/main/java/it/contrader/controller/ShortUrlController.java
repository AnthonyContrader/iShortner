package it.contrader.controller;

import java.net.MalformedURLException;

import it.contrader.main.MainDispatcher;
import it.contrader.service.ShortUrlService;
import it.contrader.view.AbstractView;

public class ShortUrlController extends AbstractView implements Controller{
	
	public void doControl(Request request) throws MalformedURLException {
		System.out.println("Inserisci url: ");
		String url = this.getInput();	
		String username = request.get("username").toString();
		ShortUrlService.createShortUrl(username, url);
		request.put("shortUrl", url);
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
