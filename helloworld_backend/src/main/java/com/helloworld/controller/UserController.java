package com.helloworld.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helloworld.dao.UserDao;
import com.helloworld.model.User;
import com.helloworld.model.Error;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			User duplicateUser = userDao.validateUsername(user.getUsername());
			if (duplicateUser != null) {
				Error error = new Error(2, "Username allready exists...please Enter Different Username");
				return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
			}
			User duplicateEmail = userDao.validateEmail(user.getEmail());
			if (duplicateEmail != null) {
				Error error = new Error(3, "Email allready exists...please Enter Different Email address");
				return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
			}
			userDao.registerUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(1, "Unable to register user details" );
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session) {
		User validuser = userDao.login(user);
		if (validuser == null) {
			Error error = new Error(4,"Invalid username/password...Please Enter Valid username/password");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		validuser.setOnline(true);
		userDao.update(validuser);
		session.setAttribute("username", validuser.getUsername());
		return new ResponseEntity<User>(validuser, HttpStatus.OK);
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?>logout(HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		User user=userDao.getUserByUsername(username);
		user.setOnline(false);
		userDao.update(user);
		session.removeAttribute("username");
		session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	@RequestMapping(value="/getuser",method=RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		User user=userDao.getUserByUsername(username);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	@RequestMapping(value="/updateuser",method=RequestMethod.PUT)
	public ResponseEntity<?>updateUser(@RequestBody User user,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			userDao.update(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(6,"Unable to edit user profile"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}