package it.contrader.converter;

import it.contrader.dto.Url_TableDTO;
import it.contrader.model.Url_Table;

public class Url_TableConverter {

	public Url_TableDTO toDTO(Url_Table urlTable){
		Url_TableDTO urlTableDto = new Url_TableDTO(urlTable.getId(), urlTable.getUrl());
		return urlTableDto;
	}
	
	public Url_Table toEntity(Url_TableDTO urlTableDto) {
		Url_Table urlTable = new Url_Table(urlTableDto.getId(), urlTableDto.getUrl());
		return urlTable;
	}
}
