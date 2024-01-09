package com.martin.Drive.service;

import com.martin.Drive.dao.UserRepository;
import com.martin.Drive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByName(username);

    return userInfo.map(UserInfoDetails::new)
            .orElseThrow(()-> new UsernameNotFoundException("No se ha encontrado usuario "+username));
    }

    public List<User> getAllUser() {return userRepository.findAll();}

    public User getUser(Long theId) {return userRepository.findById(theId).get();}
}
