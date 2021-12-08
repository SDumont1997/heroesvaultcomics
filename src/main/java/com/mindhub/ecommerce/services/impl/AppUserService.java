package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.AppUser;
import com.mindhub.ecommerce.repositories.AppUserRepository;
import com.mindhub.ecommerce.services.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppUserService implements IAppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getById(Long id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    public AppUser getByUsername(String username){
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser getByEmail(String email){
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
