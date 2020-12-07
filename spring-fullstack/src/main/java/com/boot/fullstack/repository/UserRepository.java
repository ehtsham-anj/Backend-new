package com.boot.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.fullstack.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
