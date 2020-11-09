package it.contrader.dto;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class UrlTableDTO {

	
	private int id;
	private String url;
	private String shortUrl;
	private int fk_id_user;
	
	public UrlTableDTO() {
		
	}
	
	public UrlTableDTO(String url) {
		this.url = url;
	}
	
	public UrlTableDTO(int id, String url, int fk_id_user) {
		this.id = id;
		this.url = url;
		this.fk_id_user = fk_id_user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getFk_id_user() {
		return fk_id_user;
	}

	public void setFk_id_user(int fk_id_user) {
		this.fk_id_user = fk_id_user;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	@Override
	public String toString() {
		return  id + "\t"  + url + "\t" + fk_id_user;
	}
}
