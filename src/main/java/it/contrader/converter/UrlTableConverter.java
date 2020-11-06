package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UrlTableDTO;
import it.contrader.model.UrlTable;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class UrlTableConverter {

	public static UrlTableDTO toDTO(UrlTable urlTable){
		UrlTableDTO urlTableDto = new UrlTableDTO(urlTable.getId(), urlTable.getUrl(), urlTable.getFk_id_user());
		return urlTableDto;
	}
	
	public static UrlTable toEntity(UrlTableDTO urlTableDTO) {
		UrlTable urlTable = new UrlTable(urlTableDTO.getId(), urlTableDTO.getUrl(), urlTableDTO.getFk_id_user());
		return urlTable;
	}
	
	/**
	 * Metodo per convertire le liste di url
	 */
	public static List<UrlTableDTO> toDTOList(List<UrlTable> urlList) {
		
		//Crea una lista vuota.
		List<UrlTableDTO> urlDTOList = new ArrayList<UrlTableDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(UrlTable u : urlList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			urlDTOList.add(toDTO(u));
		}
		return urlDTOList;
	}
}
