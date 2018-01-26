/**
 * FriendDetailsController
 */

app.controller('FriendDetailsController',function(FriendService,$scope,$location,$routeParams){
   var fromId=$routeParams.fromId;
   
   FriendService.getUserDetails(fromId).then(function(response){
	   $scope.user=response.data;
   },function(response){
	 if(response.status==401)
		 $location.path('/login')
		 console.log(response.status)
   })
})