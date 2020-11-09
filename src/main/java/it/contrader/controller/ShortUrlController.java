
package it.contrader.controller;

import java.net.MalformedURLException;

import it.contrader.dto.UrlTableDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ServerFraService;
import it.contrader.service.ShortUrlService;
import it.contrader.view.AbstractView;

public class ShortUrlController extends AbstractView implements Controller{
	
	public void doControl(Request request) throws MalformedURLException {
		if(request.get("url") != null){	
			int id = (int) request.get("id");
			String username = request.get("username").toString();
			UserDTO user = new UserDTO();
			user.setId(id);
			String url = request.get("url").toString();
			UrlTableDTO urlTableDto = new UrlTableDTO();
			urlTableDto.setUrl(url);
			urlTableDto = ShortUrlService.createShortUrl(user, urlTableDto);
			if(urlTableDto == null) {
				request.put("wrongUrl", "No");
				request.put("username", username);
				request.put("url", null);
				request.put("shortUrl", null);
				MainDispatcher.getInstance().callView("HomeUser", request);
			}
			else if(urlTableDto.getId() == 0 && urlTableDto.getUrl() == null && urlTableDto.getFk_id_user() == 0) {
				request.put("wrongUrl", "No");
				request.put("username", username);
				request.put("url", null);
				request.put("shortUrl", null);
				MainDispatcher.getInstance().callView("HomeUser", request);
			}else {
				ServerFraService.generator(urlTableDto);
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
	
		
	}

	@Override
	public void showOptions() {
	
		
	}

	@Override
	public void submit() {
		
		
	}

}
