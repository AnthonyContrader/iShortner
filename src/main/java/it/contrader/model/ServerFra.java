package it.contrader.model;

public class ServerFra {
	
	private int id;
	private String nome_citta;
	private int fk_id_url;
	
	
	public ServerFra(){
		
	}
	
	public ServerFra(int id, String nome_citta, int fk_id_url){
		this.id=id;
		this.nome_citta=nome_citta;
		this.fk_id_url=fk_id_url;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCitta() {
		return this.nome_citta;
	}

	public void setNomeCitta(String nomecitta) {
		this.nome_citta = nomecitta;
	}
	
	public int getFk() {
		return this.fk_id_url;
	}
	
	public void setFK(int fk_id_url) {
		this.fk_id_url=fk_id_url;
	}

	
	@Override
	public String toString() {
		return  id + "\t"  + nome_citta + "\t" + fk_id_url;
	}

}