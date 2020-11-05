package it.contrader.service;

import it.contrader.dao.UrlTableDAO;
import it.contrader.model.UrlTable;
import it.contrader.model.User;

public class ShortUrlService {

	public static String createShortUrl(String url) {		
		if(checkUrl(url)) {
			String shortUrl = "iShort.ly/"+generateRndString();
			System.out.println(shortUrl);
			UrlTable urlTable = new UrlTable();
			User user = new User();
			//System.out.println("ID: "+user.getId());
			urlTable.setUrl(url);
			UrlTableDAO urlTableDao = new UrlTableDAO();
			urlTableDao.insert(urlTable);
			return shortUrl;
		}
		return "";
	}
	
	public static String generateRndString() {
		String alg = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s = "";
		for(int i=0; i<7;i++) {
			int index = (int)(alg.length()*Math.random());
			char c = alg.charAt(index);
			s += c;
		}
		return s;
	}
	
	public static boolean checkUrl(String url) {
		String prot = "http://";
		String prot2 = "https://";
		if(url.contains(prot) || url.contains(prot2)) {
			return true;
		}
		System.out.println("Url non valido!");
		return false;
	}
}
