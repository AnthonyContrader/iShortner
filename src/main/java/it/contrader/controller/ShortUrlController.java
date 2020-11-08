
package it.contrader.controller;

import java.net.MalformedURLException;

import it.contrader.dto.UrlTableDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ShortUrlService;
import it.contrader.view.AbstractView;

public class ShortUrlController extends AbstractView implements Controller{
	
	public void doControl(Request request) throws MalformedURLException {
		if(request.get("url") != null){
			String username = request.get("username").toString();
			UserDTO user = new UserDTO();
			user.setUsername(username);
			String url = request.get("url").toString();
			UrlTableDTO urlTableDto = new UrlTableDTO();
			urlTableDto.setUrl(url);		
			urlTableDto = ShortUrlService.createShortUrl(user, urlTableDto);
			//serverFraService
			if(urlTableDto == null) { 
				request.put("username", username);
				request.put("url", null);
				request.put("wrongUrl", null);
				request.put("shortUrl", null);
				MainDispatcher.getInstance().callView("HomeUser", request);
			}
			else if(urlTableDto.getUrl() == null) {
				request.put("wrongUrl", "No");
				request.put("username", username);
				request.put("url", null);
				request.put("shortUrl", null);
				MainDispatcher.getInstance().callView("HomeUser", request);
			}else {
				request.put("shortUrl", urlTableDto.getShortUrl());
				request.put("url", null);
				request.put("wrongUrl", null);
				MainDispatcher.getInstance().callView("HomeUser", request);
			}

		}
		MainDispatcher.getInstance().callView("user.UserUrl", request);
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
