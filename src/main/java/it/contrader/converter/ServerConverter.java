package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.ServerDTO;
import it.contrader.model.Server;

@Component
public class ServerConverter extends AbstractConverter<Server, ServerDTO>{

	@Override
	public Server toEntity(ServerDTO serverDto) {
		Server server = null;
		if (serverDto != null) {
			server = new Server(serverDto.getServerId(), serverDto.getFk_id_url(), serverDto.getPosizione(), serverDto.getTipologia(), serverDto.getData());
		}
		return server;
	}

	@Override
	public ServerDTO toDTO(Server server) {
		ServerDTO serverDto = null;
		if(server != null) {
			serverDto = new ServerDTO(server.getServerId(), server.getPosizione(), server.getFkidurl(), server.getTipologia(), server.getData());
		}
		
		return serverDto;
	}

}
