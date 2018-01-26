/**
 *  BlogService
 */

 app.factory('BlogService',function($http){
	 var blogService={};
	 var BASE_URL="http://localhost:9002/helloworld_backend"
		 blogService.addBlog=function(blogPost){
		 return $http.post(BASE_URL+"/saveblogpost",blogPost)
	 }
	 blogService.getBlogPostsWaitingForApprovel=function(){
		 return $http.get(BASE_URL+"/getblogposts/"+0)
	 }
	 blogService.getBlogPostsApproved=function(){
		 return $http.get(BASE_URL+"/getblogposts/"+1)
	 }
	 blogService.getBlogPostById=function(id){
		 return $http.get(BASE_URL+"/getblogpostbyid/"+id)
	 }
	 blogService.updateBlogPost=function(blogPost){
		 return $http.put(BASE_URL+"/updateblogpost",blogPost) 
	 }
	 blogService.addComment=function(blogComment){
			console.log(blogComment)
			return $http.post(BASE_URL+"/addblogcomment",blogComment)
		}
		
		blogService.getBlogComments=function(blogPostId){
			return $http.get(BASE_URL+"/getblogcomments/"+blogPostId)
		}
	 return blogService;
 })