/**
 *  FriendService
 *  
 *  
 */

app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL="http://localhost:9002/helloworld_backend"
		
		friendService.listOfSuggestedUsers=function(){
		return $http.get(BASE_URL+"/getsuggesteduserslist")
	}
	friendService.friendRequest=function(toId){
		return $http.post(BASE_URL+"/friendrequest/"+toId)
	}
	friendService.pendingRequests=function(){
		return $http.get(BASE_URL+"/pendingrequests")
	}
	friendService.getUserDetails=function(fromId){
		return $http.get(BASE_URL+"/getuserdetails/"+fromId)
	}
	friendService.updatePendingRequest=function(pendingRequest){
		return $http.put(BASE_URL+"/updatependingrequest",pendingRequest)
	}
	friendService.listOfFriends=function(){
		return $http.get(BASE_URL+"/listoffriends")
	}
	return friendService;
	
})