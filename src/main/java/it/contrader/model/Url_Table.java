package it.contrader.model;

public class Url_Table {

	
	private int id;
	private String url;
	
	public Url_Table() {
		
	}
	
	public Url_Table(int id, String url) {
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
	
}
