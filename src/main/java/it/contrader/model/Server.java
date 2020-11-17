package it.contrader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Server {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serverId;
	
	@Column
	private Long fk_id_url;
	private String posizione;
	private String tipologia;
	private String data;
	
}
