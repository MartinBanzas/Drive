package com.martin.Drive.dao;

import com.martin.Drive.config.UserProjection;
import com.martin.Drive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository <User, Long> {


    Optional <User> findById(long theId);
    Optional<User> findByusername(String userName);

    Optional <User> findBynombre(String nombre);

    @Query("SELECT u FROM User u")
    List<UserProjection> findAllProjections();

  //  @Query("SELECT u FROM User u")
    //UserProjection findByNombre();

}
