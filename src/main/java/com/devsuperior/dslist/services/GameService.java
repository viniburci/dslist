package com.devsuperior.dslist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;


@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	public List<GameMinDTO> findAll(){
		List<GameMinDTO> games = gameRepository.findAll().stream().map(gameEntity -> new GameMinDTO(gameEntity)).toList();
		return games;
	}
	

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Optional<Game> game = this.gameRepository.findById(id);
		if(game.isPresent()) {
			GameDTO dto = new GameDTO(game.get());
			return dto;
		}
		return null;
		//throw exception
	}
	
}
