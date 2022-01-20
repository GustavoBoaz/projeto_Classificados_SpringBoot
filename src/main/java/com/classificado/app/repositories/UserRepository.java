package com.classificado.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classificado.app.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
