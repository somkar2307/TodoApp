package com.rest.webservices.restfulwebservices.dao.userdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservices.restfulwebservices.models.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	
}
