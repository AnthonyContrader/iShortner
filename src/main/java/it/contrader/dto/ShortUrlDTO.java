package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ShortUrlDTO {

	private Long id;
	private String longurl;
	private String shorturl;
	private Long fk_url;
}
