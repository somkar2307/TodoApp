package com.rest.webservices.restfulwebservices.controllers;

import java.net.URI;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservices.dao.userdao.PostRepository;
import com.rest.webservices.restfulwebservices.dao.userdao.UserDaoservice;
import com.rest.webservices.restfulwebservices.dao.userdao.UserRepository;
import com.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.webservices.restfulwebservices.models.Post;
import com.rest.webservices.restfulwebservices.models.User;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return UserRepository.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id) {
			Optional<User> user=UserRepository.findById(id);
			if(user.isEmpty())
			{
				throw new UserNotFoundException("User with id: "+id +" Not present");
			}
			EntityModel<User> entityModel=EntityModel.of(user.get());
			WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
			entityModel.add(link.withRel("all-users"));
			return entityModel;
			
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = UserRepository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Integer id) {
		UserRepository.deleteById(id);
		
	}
	
	@GetMapping("/retrievePostForUser/{id}/posts")
	public List<Post> retrievePosts(@PathVariable int id) {
		Optional<User> user=UserRepository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("No User found");
		}
		return user.get().getPosts();
		
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@Valid @RequestBody Post post) {
		
		Optional<User> user=UserRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("No User found");
		}
		post.setUser(user.get());
		Post savedPost=postRepository.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
		
	}

}
