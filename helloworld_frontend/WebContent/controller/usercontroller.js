/**
 * UserController
 */
app.controller('UserController',function(UserService,$rootScope,$scope,$location,$cookieStore){
	$scope.user={}
	
	if($rootScope.currentUser!=undefined){
	UserService.getUser().then(function(response){
		$rootScope.user=response.data
	},function(response) {
		console.log(response.status)
		$location.path('/home')
	})
}
	
	$scope.registerUser=function(){
		UserService.registerUser($scope.user).then(function(response){
			$rootScope.successMessage="Registration successful.. Please login again"
				console.log($rootScope.message)
			$location.path('/login')			
		},function(response){		
		console.log(response.data)
		console.log(response.status)
		$scope.error=response.data
		$location.path('/register')
		})
		
	}
	$scope.validateUser=function(){
		UserService.validateUser($scope.user).then(function(response){			
			console.log(response.data)
			$rootScope.currentUser=response.data
			$cookieStore.put("currentUser",response.data)
			$location.path('/home')
		},function(response){
			console.log(response.status)
			$scope.error=response.data			
			$location.path('/login')			
		})		
	}
	$scope.updateUser=function(){
		UserService.updateUser($rootScope.currentUser).then(function(response){
			alert("update successfully")
			$location.path('/home')
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/login')
			$location.path('/editprofile')	
			
		})
	}
})
