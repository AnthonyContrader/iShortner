package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ServerFraDTO;
import it.contrader.model.ServerFra;



public class ServerFraConverter {

	public static ServerFraDTO toDTO(ServerFra server) {
		ServerFraDTO serverDTO = new ServerFraDTO(server.getId(), server.getNomeCitta(), server.getFk());
		return serverDTO;
	}
	
	public static ServerFra toEntity(ServerFraDTO serverDTO) {
		ServerFra server = new ServerFra(serverDTO.getId(), serverDTO.getNomeCitta(), serverDTO.getFk());
		return server;
	}
	
	public List<ServerFraDTO> toDTOList(List<ServerFra> serverList) {
		List<ServerFraDTO> serverDTOList = new ArrayList<ServerFraDTO>();
		for(ServerFra server : serverList) {
			serverDTOList.add(toDTO(server));
		}
		return serverDTOList;
	}
	
}
