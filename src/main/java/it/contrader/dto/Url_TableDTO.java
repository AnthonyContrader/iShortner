package it.contrader.dto;

public class Url_TableDTO {

	
	private int id;
	private String url; 
	
	public Url_TableDTO() {
		
	}
	
	public Url_TableDTO(String url) {
		this.url = url;
	}
	
	public Url_TableDTO(int id, String url) {
		this.id = id;
		this.url = url;
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
	
	@Override
	public String toString() {
		return  id + "\t"  + url;
	}
}
