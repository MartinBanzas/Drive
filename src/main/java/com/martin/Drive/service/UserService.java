package com.martin.Drive.service;

import com.martin.Drive.config.UserProjection;
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
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByusername(username);

        return userInfo.map(UserIDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("No se ha encontrado usuario "+username));
    }


    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuario añadido con éxito";
    }

    public List<User> getAllUser() {return userRepository.findAll();}

    public List<UserProjection> getUsersProjection() {
        return userRepository.findAllProjections();
    }

    public void updateScore(User player) {
        Optional<User> optionalUser = userRepository.findBynombre(player.getNombre());
        optionalUser.ifPresent(user -> {
            user.setPuntuacion(player.getPuntuacion());
            userRepository.save(user);
        });
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUser(Long theId) {return userRepository.findById(theId).get();}


   public Optional <User> getUserByName(String nombre) {return userRepository.findBynombre(nombre);}
}

