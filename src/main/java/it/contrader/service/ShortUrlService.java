package it.contrader.service;

public class ShortUrlService {

	public String createShortUrl(String url) {
		checkUrl(url);
		String shortUrl = "iShort.ly/"+generateRndString();
		return shortUrl;
	}
	
	public String generateRndString() {
		String alg = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s = "";
		for(int i=0; i<7;i++) {
			int index = (int)(alg.length()*Math.random());
			char c = alg.charAt(index);
			s += c;
		}
		return s;
	}
	
	public boolean checkUrl(String url) {
		String prot = "http://";
		String prot2 = "https://";
		if(url.contains(prot) || url.contains(prot2)) {
			return true;
		}
		System.out.println("Url non valido!");
		return false;
	}
}
