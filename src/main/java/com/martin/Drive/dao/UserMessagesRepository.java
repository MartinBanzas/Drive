package com.martin.Drive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessagesRepository extends JpaRepository <UserMessagesRepository, Long> {

    List<UserMessagesRepository> findAll();

}
