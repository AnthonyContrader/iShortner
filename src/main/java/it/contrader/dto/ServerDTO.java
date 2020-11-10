package it.contrader.dto;

/**
 * 
 * @author Valeria
 *
 */

public class ServerDTO {


	/**
	 * Qui sotto definisco gli attributi di Server. 
	 */
	private int id;

	private String posizione;
	
	private String tipologia;
	
	private String data;
	
	private int fkIdUrl;

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Server
	 */
	public ServerDTO() {
		
	}

	public ServerDTO (String posizione, String tipologia, String data, int fkIdUrl) {
		this.posizione = posizione;
		this.tipologia = tipologia;
		this.data = data;
		this.fkIdUrl=fkIdUrl;
	}

	public ServerDTO (int id, String posizione, String tipologia, String data, int fkIdUrl) {
		this.id = id;
		this.posizione = posizione;
		this.tipologia = tipologia;
		this.data = data;
		this.fkIdUrl=fkIdUrl;
	}

	/**
	 * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di User
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getFkIdUrl() {
		return fkIdUrl;
	}

	public void setFkIdUrl(int fkIdUrl) {
		this.fkIdUrl = fkIdUrl;
	}
	
	
	
	
	//Trasforma un oggetto in una stringa
	@Override
	public String toString() {
		return  id + "\t"  + posizione +"\t\t" +   tipologia + "\t\t" + data +"\t\t" + fkIdUrl;
	}

	
	
}
