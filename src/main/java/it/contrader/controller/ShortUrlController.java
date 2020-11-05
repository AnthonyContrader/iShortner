package it.contrader.controller;

public class ShortUrlController {

	public String createShortUrl() {
		generateRndString();
	}
	
	public String generateRndString() {
		String alg = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String short = "";
		for(int i=0; i<7;i++) {
			int index = (int)(alg.length()*Math.random());
			char c = alg.indexOf(index);
			short += c;
		}
	}
}
