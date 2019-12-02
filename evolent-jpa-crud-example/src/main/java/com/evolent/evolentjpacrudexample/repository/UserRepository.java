package com.evolent.evolentjpacrudexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evolent.evolentjpacrudexample.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
