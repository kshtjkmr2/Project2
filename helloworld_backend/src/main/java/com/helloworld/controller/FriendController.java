package com.helloworld.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.dao.FriendDao;
import com.helloworld.dao.UserDao;
import com.helloworld.model.Error;
import com.helloworld.model.Friend;
import com.helloworld.model.User;

@RestController
public class FriendController {
	@Autowired
	private FriendDao friendDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/getsuggesteduserslist", method = RequestMethod.GET)
	public ResponseEntity<?> getListSuggestedUserList(HttpSession session) {
		if (session.getAttribute("username") == null) {
			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try {
			String username = (String) session.getAttribute("username");
			List<User> suggestedUser = friendDao.getlistOfSuggestedUser(username);
			return new ResponseEntity<List<User>>(suggestedUser, HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(6, "Unable to find ");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/friendrequest/{toId}", method = RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@PathVariable String toId, HttpSession session) {
		if (session.getAttribute("username") == null) {
			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try {
			String username = (String) session.getAttribute("username");
			friendDao.sendFriedRequest(username, toId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(6, "Unable to send request");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequest(HttpSession session){
		if (session.getAttribute("username") == null) {
			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try {
			String username = (String) session.getAttribute("username");
			List<Friend> pendingRequest=friendDao.listOfPeendingRequest(username);
			return new ResponseEntity<List<Friend>>(pendingRequest,HttpStatus.OK);
		} catch (Exception e) {
			Error error = new Error(6, "Unable to send request");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/getuserdetails/{fromId}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserDetails(@PathVariable String fromId,HttpSession session){
		if (session.getAttribute("username") == null) {
			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try{
			User user=userDao.getUserByUsername(fromId);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch (Exception e) {
			Error error = new Error(6, "Unable to get details of user");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value="/updatependingrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> updateRequest(@RequestBody Friend pendingRequest,HttpSession session){
		if (session.getAttribute("username") == null) {
			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try{
			friendDao.updateRequest(pendingRequest);
			return new ResponseEntity<Friend>(pendingRequest,HttpStatus.OK);
		}
		catch (Exception e) {
			Error error = new Error(6, "Unable to update request");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@RequestMapping(value="/listoffriends",method=RequestMethod.GET)
	public ResponseEntity<?> getFriendList(HttpSession session){
		if (session.getAttribute("username") == null) {
			Error error = new Error(5, "Unauthorized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		try{
			String username = (String) session.getAttribute("username");
			List<Friend> friends=friendDao.getFriendsList(username);
			return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
		}
		catch (Exception e) {
			Error error = new Error(6, "Unable to find list");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
      
}
