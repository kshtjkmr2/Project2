/**
 *  BlogController
 */

 app.controller('BlogController',function(BlogService,$scope,$location){
	 
	 BlogService.getBlogPostsWaitingForApprovel().then(function(response){
		$scope.blogPostsWaitingForApprovel=response.data;
	 },function(response){
		 if(response.status==401)
			 $location.path('/login')
	 })
	 
	 BlogService.getBlogPostsApproved().then(function(response){
		 $scope.blogPostsApproved=response.data;
	 },function(response){
		 if(response.status==401)
			 $location.path('/login')
	 })
	 
	 $scope.addBlog=function(){
		 BlogService.addBlog($scope.blogPost).then(function(response){
			 console.log(response.status)
			 alert("Blog is successfylly posted..waiting for approvel")
			 $location.path('/getallblogs')
		 },function(response){
			 if(response.status==401)
				 $location.path('/login')
			alert("Do you want to leave without any post")
			$location.path('/home')
		 })
	 }
 })