package it.contrader.service;

import java.util.List;

import it.contrader.converter.ServerFraConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dao.ServerFraDAO;
import it.contrader.dao.UserDAO;
import it.contrader.dto.ServerFraDTO;
import it.contrader.dto.UserDTO;

public class ServerFraService {
	
	private ServerFraDAO serverDAO;
	private ServerFraConverter serverConverter;
	
	//Istanzio DAO  e Converter specifici.
	public ServerFraService(){
		this.serverDAO = new ServerFraDAO();
		this.serverConverter = new ServerFraConverter();
	}
	

	public List<ServerFraDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return serverConverter.toDTOList(serverDAO.getAll());
	}


	public ServerFraDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return serverConverter.toDTO(serverDAO.read(id));
	}


	public boolean insert(ServerFraDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return serverDAO.insert(serverConverter.toEntity(dto));
	}


	/*public boolean update(ServerFraDTO dto) {
		// Converte un userDTO in entit� e lo passa allo userDAO per la modifica
		return serverDAO.update(serverConverter.toEntity(dto));
	}*/


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return serverDAO.delete(id);
	}

}
