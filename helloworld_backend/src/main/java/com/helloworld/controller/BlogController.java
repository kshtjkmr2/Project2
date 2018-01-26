package com.helloworld.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helloworld.dao.BlogPostDao;
import com.helloworld.dao.UserDao;
import com.helloworld.model.BlogComment;
import com.helloworld.model.BlogPost;
import com.helloworld.model.User;
import com.helloworld.model.Error;

@Controller
public class BlogController {
 
	@Autowired
	private BlogPostDao blogPostDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/saveblogpost",method=RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		User user=userDao.getUserByUsername(username);		
		blogPost.setPostedOn(new Date());
		blogPost.setPostedBy(user);
		try{
			blogPostDao.saveBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(6,"Unable to Insert");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	
	@RequestMapping(value="/getblogposts/{approved}")
	public ResponseEntity<?>getBlogPosts(@PathVariable int approved,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogPost=blogPostDao.getBlogPosts(approved);
		return new ResponseEntity<List<BlogPost>>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getblogpostbyid/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPostById(@PathVariable int id,HttpSession session){		
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	
		BlogPost blogPost=blogPostDao.getBlogPostById(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	@RequestMapping(value="/updateblogpost",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	
		try{
			blogPostDao.updateBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(6,"Unable to update"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/addblogcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		String username=(String)session.getAttribute("username");
		User user=userDao.getUserByUsername(username);
		blogComment.setCommentedBy(user);//set the value for foreign key 'username' in blogcomment table
		blogComment.setCommentedOn(new Date());//set the value for commentedOn
		try{
		blogPostDao.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}catch(Exception e){
			Error error=new Error(7,"Unable to add blogcomment " + e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
   @RequestMapping(value="/getblogcomments/{blogPostId}")
	public ResponseEntity<?> getBlogComments(@PathVariable int blogPostId,HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"UnAuthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);//401 - 2nd call back func will be executed
		}
		List<BlogComment> blogComments=blogPostDao.getAllBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	
}
