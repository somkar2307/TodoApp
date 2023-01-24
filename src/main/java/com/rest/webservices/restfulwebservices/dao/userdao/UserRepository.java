package com.rest.webservices.restfulwebservices.dao.userdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restfulwebservices.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
