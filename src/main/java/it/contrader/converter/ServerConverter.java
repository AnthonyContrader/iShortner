package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ServerDTO;
import it.contrader.model.Server;
/**
 * 
 * @author Valeria
 * 

 *
 */

public class ServerConverter implements Converter<Server, ServerDTO> {

	@Override
	public ServerDTO toDTO(Server server) {
		ServerDTO serverDTO = new ServerDTO(server.getId(), server.getPosizione(), server.getTipologia(),server.getData(),server.getFkIdUrl());
		return serverDTO;
	}

	@Override
	public Server toEntity(ServerDTO serverDTO) {
		Server server = new Server(serverDTO.getId(),serverDTO.getPosizione(),serverDTO.getTipologia(),serverDTO.getData(),serverDTO.getFkIdUrl());
		return server;
	}

	@Override
	public List<ServerDTO> toDTOList(List<Server> serverList) {
		List<ServerDTO> serverDTOList = new ArrayList<ServerDTO>();
		
		for (Server server : serverList) {
			serverDTOList.add(toDTO(server));
		}
		return serverDTOList;
	}


	
	
}
