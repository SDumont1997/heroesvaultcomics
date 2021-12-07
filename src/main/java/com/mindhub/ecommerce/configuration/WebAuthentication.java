package com.mindhub.ecommerce.configuration;

import com.mindhub.ecommerce.models.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import com.mindhub.ecommerce.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(inputName->{
            AppUser appUser = appUserRepository.findByEmail(inputName);

            if(appUser != null){
                if (appUser.isAdmin()){
                    return new User(appUser.getUsername(), appUser.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
                } else {
                    return new User(appUser.getUsername(), appUser.getPassword(), AuthorityUtils.createAuthorityList("USER"));
                }
            } else throw new UsernameNotFoundException("Unknown user: " + inputName);

        });
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
