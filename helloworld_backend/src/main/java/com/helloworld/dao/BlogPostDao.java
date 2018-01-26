package com.helloworld.dao;

import java.util.List;

import com.helloworld.model.BlogComment;
import com.helloworld.model.BlogPost;

public interface BlogPostDao {

	void saveBlogPost(BlogPost blogPost);
	
	List<BlogPost>getBlogPosts(int approved);
	
	BlogPost getBlogPostById(int id);

	void updateBlogPost(BlogPost blogPost);
	
	void addBlogComment(BlogComment blogComment);

	List<BlogComment> getAllBlogComments(int blogPostId);
}
