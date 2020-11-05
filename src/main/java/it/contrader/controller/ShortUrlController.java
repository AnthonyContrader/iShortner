package it.contrader.controller;

import it.contrader.main.MainDispatcher;
import it.contrader.service.ShortUrlService;
import it.contrader.view.AbstractView;

public class ShortUrlController extends AbstractView{
	
	private ShortUrlService shortServ;
	
	public void doControl(Request request) {
		System.out.println("Inserisci url: ");
		String url = this.getInput();	
		request.put("shortUrl", shortServ.createShortUrl(url));
		MainDispatcher.getInstance().callView("ShortUrl", request);
		System.out.println("Ciao");
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
