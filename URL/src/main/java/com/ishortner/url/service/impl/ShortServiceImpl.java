package com.ishortner.url.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ishortner.url.repository.UrlRepository;
import com.ishortner.url.service.dto.UrlDTO;
import com.ishortner.url.service.mapper.UrlMapper;
import com.ishortner.url.web.rest.UrlResource;

@Service
@Transactional
public class ShortServiceImpl {
	
	private final Logger log = LoggerFactory.getLogger(UrlResource.class);
	
	@Autowired
	private UrlRepository urlRepo;
	
	@Autowired
	private UrlMapper urlMap;
	
	
	
	
	public UrlDTO createShortUrl(UrlDTO url) throws MalformedURLException {	
		UrlDTO urlTableDto = new UrlDTO();
		if(isReachable(url.getLongurl()) ) { 
			String shortUrl = "iShort.ner/" + generateRndString();
			boolean a = chkShort(shortUrl);
			if(!a) {
				String longUrl = url.getLongurl();
				if(!longUrl.contains("http") && !longUrl.contains("https")) {
					longUrl = "http://" + longUrl;
				}
				urlTableDto.setLongurl(longUrl);
				urlTableDto.setShorturl(shortUrl);
				urlTableDto.setFkuser(url.getFkuser());
				urlTableDto = urlMap.toDto(urlRepo.save(urlMap.toEntity(urlTableDto)));
				return urlTableDto;
			}else {
				createShortUrl(url);			
			}
		}
		return null;
	}

	public boolean chkShort(String shortUrl) {
		return urlRepo.existsByShorturl(shortUrl);
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

	public List<UrlDTO> readList(Long id){
		return urlMap.toDto(urlRepo.findAllByFkuser(id));
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
	
	/**
	 * 
	 * @param urlDto
	 * @return restituisce un Long Url
	 * 
	 * Attiva il converter-->Mapper e pone in ingresso uno Short Url
	 */
	public UrlDTO findLongUrl(UrlDTO urlDto) {
		return  urlMap.toDto(urlRepo.findByShorturl(urlDto.getShorturl()));
	}

}
