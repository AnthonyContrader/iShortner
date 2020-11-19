package it.contrader.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ShortUrlConverter;
import it.contrader.dao.ShortUrlRepository;
import it.contrader.dto.ShortUrlDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.ShortUrl;

@Service
public class ShortUrlService extends AbstractService<ShortUrl, ShortUrlDTO> {


	@Autowired
	private ShortUrlRepository repo;
	@Autowired 
	private ShortUrlConverter conv; 
	
	public ShortUrlDTO createShortUrl(UserDTO user, ShortUrlDTO url) throws MalformedURLException {	
		ShortUrlDTO urlTableDto = new ShortUrlDTO();
		if(isReachable(url.getLongurl()) ) { 
			String shortUrl = "iShort.ly/"+generateRndString();
			String longUrl = url.getLongurl();
			if(!longUrl.contains("http") && !longUrl.contains("https")) {
				longUrl = "http://" + longUrl;
			}
			boolean a = (Boolean) repo.existsByLongurlAndFkurl(longUrl, user.getId());
			if(!a) {
				urlTableDto.setLongurl(longUrl);
				urlTableDto.setFk_url(user.getId());
				urlTableDto.setShorturl(shortUrl);
				urlTableDto = insert(urlTableDto);
				return urlTableDto;
			}
		}
		return urlTableDto;
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
	
	public ShortUrlDTO getLongUrl(ShortUrlDTO url) {
		return conv.toDTO(repo.findByShorturl(url.getShorturl()));
	}
	
//	
//	public static boolean checkUrl(String url) {
//		if(url.contains("http") || url.contains("https")) {
//			return true;
//		}
//		System.out.println("Url non valido!");
//		return false;
//	}
	
	
	public List<ShortUrlDTO> readList(Long id){
		return conv.toDTOList(repo.findAllByFkurl(id));
	}
	
	public boolean isReachable(String url) throws MalformedURLException {
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
        		Pattern pat = Pattern.compile("\\(([0-9]{1,3}\\.){3}[0-9]{1,3}\\) | ([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])");
        		Matcher m = pat.matcher(s);
        		if (m.find()) {
        			return true;
        		}
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
