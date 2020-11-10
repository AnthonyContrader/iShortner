package it.contrader.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import it.contrader.converter.UrlTableConverter;
import it.contrader.dao.UrlDAO;
import it.contrader.dto.UrlTableDTO;
import it.contrader.dto.UserDTO;

public class ShortUrlService {


	public static UrlTableDTO createShortUrl(UserDTO user, UrlTableDTO url) throws MalformedURLException {	
		UrlTableDTO urlTableDto = new UrlTableDTO();
		if(isReachable(url.getLongUrl()) ) { 
			String shortUrl = "iShort.ly/"+generateRndString();
			urlTableDto.setLongUrl(url.getLongUrl());
			urlTableDto.setFkIdUser(user.getId());
			urlTableDto.setShortUrl(shortUrl);
			urlTableDto = UrlTableConverter.toDTO(UrlDAO.insert(UrlTableConverter.toEntity(urlTableDto)));
			if(urlTableDto.getId() == 0 && urlTableDto.getLongUrl() == null) {
				return urlTableDto;
			}
			return urlTableDto;
		}
		return null;
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
	
//	
//	public static boolean checkUrl(String url) {
//		if(url.contains("http") || url.contains("https")) {
//			return true;
//		}
//		System.out.println("Url non valido!");
//		return false;
//	}
	
	
//	public static List<UrlTableDTO> read(int id) {
//		return UrlTableConverter.toDTOList(UrlTableDAO.read(id));
//	}
	
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
            	System.out.println("6");
                if (s.contains("Packets: Sent") || s.contains("bytes of data") || s.contains("data bytes")) {
                    return true;
                }
                System.out.println("7");
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
