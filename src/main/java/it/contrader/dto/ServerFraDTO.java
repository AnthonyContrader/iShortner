package it.contrader.dto;

public class ServerFraDTO {

	private int id;
	private String nome_citta;
	private String fk_id_url;
	
	public ServerFraDTO(){
		
	}
	
	public ServerFraDTO(int id, String nome_citta, String fk_id_url){
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
		return nome_citta;
	}

	public void setNomeCitta(String nomecitta) {
		this.nome_citta = nomecitta;
	}
	
	public String getFk() {
		return this.fk_id_url;
	}
	
	public void setFK(String username) {
		this.fk_id_url=username;
	}

	
	@Override
	public String toString() {
		return  id + "\t"  + nome_citta;
	}
}
