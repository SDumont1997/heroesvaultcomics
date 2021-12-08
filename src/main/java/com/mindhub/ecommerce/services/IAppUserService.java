package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.AppUser;

import java.util.List;

public interface IAppUserService {
    List<AppUser> getAll();
    AppUser getById(Long id);
    AppUser getByUsername(String username);
    AppUser getByEmail(String email);
    AppUser save(AppUser appUser);
}
