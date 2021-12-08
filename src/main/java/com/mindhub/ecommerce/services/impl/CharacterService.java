package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.repositories.CharacterRepository;
import com.mindhub.ecommerce.services.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService implements ICharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Override
    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character getById(Long id) {
        return characterRepository.findById(id).get();
    }

    @Override
    public Character save(Character character) {
        return characterRepository.save(character);
    }
}
