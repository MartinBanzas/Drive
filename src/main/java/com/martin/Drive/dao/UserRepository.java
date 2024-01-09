package com.martin.Drive.dao;

import com.martin.Drive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {


    Optional <User> findById(long theId);
    Optional<User> findByusername(String userName);

}
