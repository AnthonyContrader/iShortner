package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.Url_TableDTO;
import it.contrader.model.Url_Table;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class Url_TableConverter {

	public Url_TableDTO toDTO(Url_Table urlTable){
		Url_TableDTO urlTableDto = new Url_TableDTO(urlTable.getId(), urlTable.getUrl(), urlTable.getFk_id_user());
		return urlTableDto;
	}
	
	public Url_Table toEntity(Url_TableDTO urlTableDTO) {
		Url_Table urlTable = new Url_Table(urlTableDTO.getId(), urlTableDTO.getUrl(), urlTableDTO.getFk_id_user());
		return urlTable;
	}
	
	/**
	 * Metodo per convertire le liste di url
	 */
	public List<Url_TableDTO> toDTOList(List<Url_Table> urlList) {
		
		//Crea una lista vuota.
		List<Url_TableDTO> urlDTOList = new ArrayList<Url_TableDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Url_Table u : urlList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			urlDTOList.add(toDTO(u));
		}
		return urlDTOList;
	}
}
