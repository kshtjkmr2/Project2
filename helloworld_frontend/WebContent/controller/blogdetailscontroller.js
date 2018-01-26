/**
 * BlogDetailController
 */

app.controller('BlogPostDetailController', function(BlogService, $scope,
		$location, $routeParams) {

	var id = $routeParams.id
	$scope.showComments=false
	
	$scope.blogPost = BlogService.getBlogPostById(id).then(function(response) {
		$scope.blogPost = response.data
		console.log(response.data)
	}, function(response) {
		console.log(response.status)
		if (response.status == 401)
			$location.path('/login')
	})
	
	$scope.updateBlogPost=function(){
		BlogService.updateBlogPost($scope.blogPost).then(function(response){
			console.log(response.status)
			$location.path('/getallblogs')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	function getBlogComments(){
		BlogService.getBlogComments(id).then(function(response){
			$scope.blogComments=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
			console.log(response.status)
		})
	}
	$scope.getComments=function(){
		$scope.showComments=true;
	}
	getBlogComments()
	$scope.updateBlogPost=function(){
		console.log($scope.blogPost)
		BlogService.updateBlogPost($scope.blogPost).then(function(response){
			console.log(response.status)
			$location.path('/getallblogs')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.addComment=function(){
		/*
		 * Assignment statement which will assign blogPost object to blogPost property in blogcomment object
		 * $scope.blogPost is an JSON object of type BlogPost
		 * $scope.blogComment is an JSON object of type BlogComment
		 * blogComment has {id , blogPost, commentedBy, commentedOn, commentText }
		 * $scope.blogComment.blogPost=$scope.blogPost
		 */
		$scope.blogComment.blogPost=$scope.blogPost
		/*
		 * blogComment : {blogPost:{id:'',blogTitle:'',postedBy:'',postedOn:''},commentText:'Good'}
		 */
		console.log($scope.blogComment)
		BlogService.addComment($scope.blogComment).then(function(response){
			$scope.blogComment.commentText=''
				getBlogComments()
			console.log(response.data)
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
			$location.path('/getblogpostbyid/'+id)
		})
	}

})