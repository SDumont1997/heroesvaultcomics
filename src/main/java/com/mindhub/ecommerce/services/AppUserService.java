package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.AppUser;

import java.util.List;

public interface AppUserService {
    public List<AppUser> getAll();
    public AppUser getById(Long id);
    public AppUser save(AppUser appUser);
}
