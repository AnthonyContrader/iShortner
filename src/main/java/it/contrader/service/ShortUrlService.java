package it.contrader.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import it.contrader.controller.Request;
import it.contrader.converter.UrlTableConverter;
import it.contrader.dao.UrlTableDAO;
import it.contrader.dto.UrlTableDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.UrlTable;
import it.contrader.model.User;

public class ShortUrlService {

	static Request request;

	public static String createShortUrl(String username, String url) throws MalformedURLException {	
		if(isReachable(url)) {
			String shortUrl = "iShort.ly/"+generateRndString();
			System.out.println(shortUrl);
			UrlTableDTO urlTableDto = new UrlTableDTO();
			urlTableDto.setUrl(url);
			urlTableDto.setFk_id_user(username);
			UrlTableDAO.insert(UrlTableConverter.toEntity(urlTableDto));
			//sdhadhhas
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
	
//	public static boolean checkUrl(String url) {
//		String prot = "http://";
//		String prot2 = "https://";
//		if(url.contains(prot) || url.contains(prot2)) {
//			return true;
//		}
//		System.out.println("Url non valido!");
//		return false;
//	}
//	
	
	//sdhhasdhhasdh
	public static UrlTableDTO read(int id) {
		return UrlTableConverter.toDTO(UrlTableDAO.read(id));
	}
	
	public static boolean isReachable(String url) throws MalformedURLException {
        if (!url.contains("http") && !url.contains("https")) {
            url = "http://" + url;
        }
        URL urlObj = new URL(url);
        url = urlObj.getHost();
        url = "ping " + url;
        try {
            Process p = Runtime.getRuntime().exec(url);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String s = "";
            while ((s = inputStream.readLine()) != null) {
                if (s.contains("Packets: Sent") || s.contains("bytes of data")) {
                    return true;
                }
            }
            System.out.println("Url non valido!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
