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
	
	public static UrlTableDTO toDTO(UrlTable url) {
		UrlTableDTO urlTableDTO = new UrlTableDTO(url.getId(), url.getLongUrl(), url.getShortUrl(), url.getFkIdUser());
		return urlTableDTO;
	}


	public static UrlTable toEntity(UrlTableDTO urlDTO) {
		UrlTable url = new UrlTable(urlDTO.getId(), urlDTO.getLongUrl(), urlDTO.getShortUrl(), urlDTO.getFkIDUser());
		return url;
	}
	
	
	public List<UrlTableDTO> toDTOList(List<UrlTable> urlList) {
		//Crea una lista vuota.
		List<UrlTableDTO> urlDTOList = new ArrayList<UrlTableDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(UrlTable urlTable : urlList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			urlDTOList.add(toDTO(urlTable));
		}
		return urlDTOList;
	}

}
