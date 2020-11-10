package it.contrader.servlets;

import javax.servlet.http.HttpServlet;

/**
 * @author angelarusso
 */

public class UrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UrlServlet() {
	}
	
	public void createShortUrl(String user, String url) {
			String shortUrl = "iShort.ly/" + randomStr();
	}
	
	public static String randomStr() {
		String alg = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s = "";
		for(int i=0; i<7; i++) {
			int index = (int)(alg.length()*Math.random());
			char c = alg.charAt(index);
			s+= c;
		}
		return s;
	}

}
