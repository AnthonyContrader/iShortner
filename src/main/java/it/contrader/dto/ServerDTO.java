package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ServerDTO {
	
		
		private Long serverId;
		
		private String posizione;
		
		private Long fk_id_url; 
		
		private String tipologia;
		
		private String data;
}
