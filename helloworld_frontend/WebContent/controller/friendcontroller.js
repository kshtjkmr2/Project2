/**
 * FriendController
 */

app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUsers(){
		
		FriendService.listOfSuggestedUsers().then(function(response){
			$scope.suggestedusers=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
			console.log(response.status)	
		})
	}
	
	function pendingRequests(){
		$scope.listOfPendingRequest=FriendService.pendingRequests().then(function(response){
			$scope.listOfPendingRequest=response.data
		},function(response){
			console.log(response.status);
		})
	}
	function listOfFriends(){
		$scope.friendsList=FriendService.listOfFriends().then(function(response){
			console.log(response.data)
			$scope.friendsList=response.data
		},function(response){
			console.log(response.status)
		})
				
	}
	$scope.friendRequest=function(toId){
		FriendService.friendRequest(toId).then(function(response){
			alert("Request has been send successfully")
			listOfSuggestedUsers();
			$location.path('/suggestedfriend')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			console.log(response.status)
		})
	}
	$scope.updatePendingRequest=function(pendingRequest,status){
		console.log(pendingRequest.status)
		pendingRequest.status=status
		
		FriendService.updatePendingRequest(pendingRequest).then(function(response){
			alert("Are You conferm ")
			pendingRequests()
			$location.path('/pendingrequest')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			console.log(response.status)
		})
	}
	listOfFriends()
	pendingRequests()
	listOfSuggestedUsers()
})