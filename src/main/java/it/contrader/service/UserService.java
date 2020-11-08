package it.contrader.service;

import java.util.List;


import it.contrader.converter.UserConverter;
import it.contrader.dao.UserDAO;
import it.contrader.dto.UserDTO;

/**
 * 
 * @author Vittorio, De Santis
 *
 *Grazie all'ereditariet� mi basta specificare i tipi di questa classe per
 *ereditare i metodi della clase AbstractService. Pertanto la classe risulta meno complicata
 *da scrivere, facendoci risparmiare tempo e fatica!
 */
public class UserService {
	
	private UserDAO userDAO;
	private UserConverter userConverter;
	
	//Istanzio DAO  e Converter specifici.
	public UserService(){
		this.userDAO = new UserDAO();
		this.userConverter = new UserConverter();
	}
	

	public List<UserDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return userConverter.toDTOList(userDAO.getAll());
	}


	public UserDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return userConverter.toDTO(userDAO.read(id));
	}


	public boolean insert(UserDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return userDAO.insert(userConverter.toEntity(dto));
	}


	public boolean update(UserDTO dto) {
		// Converte un userDTO in entit� e lo passa allo userDAO per la modifica
		return userDAO.update(userConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return userDAO.delete(id);
	}
	

}
