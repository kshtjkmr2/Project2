package com.helloworld.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.helloworld.dao.ProfilePictureDao;
import com.helloworld.model.User;
import com.helloworld.model.Error;
import com.helloworld.model.ProfilePicture;;

@Controller
public class ProfilePictureController {
 
	@Autowired
	private ProfilePictureDao profilePictureDao;
	
		@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
			System.out.println("upload profile pic "+session.getAttribute("username"));
			if(session.getAttribute("username")==null){
				Error error=new Error(5,"UnAuthorized User");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			}
		String username=(String) session.getAttribute("username");
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setUsername(username);
		profilePicture.setImage(image.getBytes());
		try{
		profilePictureDao.saveProfilePicture(profilePicture);
		return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(7,"Unable to insert image"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
		//http://localhost:8080/backend_project2/getimage/admin
		@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
		public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
			System.out.println("getting profile pic "+session.getAttribute("username"));			
			if(session.getAttribute("username")==null)
				return null;
			else
			{
				ProfilePicture profilePic=profilePictureDao.getProfilePicture(username);
				if(profilePic==null)
					return null;
				else
					return profilePic.getImage();
			}
			
	}
}
