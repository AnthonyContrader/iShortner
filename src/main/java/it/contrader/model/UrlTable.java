package it.contrader.model;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class UrlTable {

	/**
	 * Definisco gli attributi della Tabella url
	 */
	
	private int id;
	private String url;
	private int fk_id_user;
	
	
	public UrlTable() {
		
	}
	
	public UrlTable(int id, String url, int fk_id_user) {
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

	
	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}
	
	
	
}