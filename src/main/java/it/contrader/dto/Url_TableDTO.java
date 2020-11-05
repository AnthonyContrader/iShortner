package it.contrader.dto;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class Url_TableDTO {

	
	private int id;
	private String url;
	private int fk_id_user;
	
	public Url_TableDTO() {
		
	}
	
	public Url_TableDTO(String url) {
		this.url = url;
	}
	
	public Url_TableDTO(int id, String url, int fk_id_user) {
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
	
	@Override
	public String toString() {
		return  id + "\t"  + url + "\t" + fk_id_user;
	}
}
