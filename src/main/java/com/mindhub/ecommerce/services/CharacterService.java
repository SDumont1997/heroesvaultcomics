package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Character;

import java.util.List;

public interface CharacterService {

    public List<Character> getAll();
    public Character getById(Long id);
    public Character save(Character character);
}
