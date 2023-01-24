package com.rest.webservices.restfulwebservices.dao.userdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.rest.webservices.restfulwebservices.models.User;

@Component
public class UserDaoservice {
	
	
	private static List<User> users=new ArrayList<>();
	private static int count=0;
	
	static {
		users.add(new User(count++,"Adam",LocalDate.now().minusYears(20)));
		users.add(new User(count++,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(count++,"Adam",LocalDate.now().minusYears(10)));
	}
	
	public List<User> findAll(){
		return users;
	}

	public User findUserById(Integer id) {
		Predicate<? super User> predicate=user->user.getId()==id;
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(count++);
		users.add(user);
		int temp=count-1;
		return findUserById(temp);
		
	}

	public void removeById(Integer id) {
		
		Predicate<? super User> predicate=user->user.getId()==id;
		users.removeIf(predicate);
		
	}
}
