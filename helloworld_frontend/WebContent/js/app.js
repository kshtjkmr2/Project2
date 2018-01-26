/**
 * Angular JS module routeprovider cofiguration
 */

var app=angular.module("app",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	$routeProvider
	.when('/home',{
		templateUrl:'views/home.html',
		controller:'UserController'
	})
	.when('/aboutus',{
		templateUrl:'views/aboutus.html'
	})
	.when('/register',{
		templateUrl:'views/registration.html',
		controller:'UserController'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'UserController'
		
	})
	.when('/editprofile',{
		templateUrl:'views/updateprofile.html',
		controller:'UserController'
	})
	.when('/savejob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
	})
	.when('/getalljobs',{
		templateUrl:'views/joblist.html',
		controller:'JobController'
	})
	.when('/getblogpost',{
		templateUrl:'views/blogpostform.html',
		controller:'BlogController'
	})
	.when('/getallblogs',{
		templateUrl:'views/listofblogpost.html',
		controller:'BlogController'
	})
	.when('/approveblogpost/:id',{
		templateUrl:'views/approvalform.html',
		controller:'BlogPostDetailController'
	})
	.when('/getblogpostbyid/:id',{
		templateUrl:'views/blogdetail.html',
		controller:'BlogPostDetailController'
	})
	.when('/profilepic',{
		templateUrl:'views/profilepicture.html'
	})
	.when('/suggestedfriend',{
		templateUrl:'views/friendlist.html',
		controller:'FriendController'
	})
	.when('/friendlist',{
		templateUrl:'views/confermfriendlist.html',
		controller:'FriendController'
	})
	.when('/pendingrequest',{
		templateUrl:'views/pendingrequest.html',
		controller:'FriendController'
	})
	.when('/getUserDetails/:fromId',{
		templateUrl:'views/userdetails.html',
		controller:'FriendDetailsController'
	})
	
	.otherwise({
		templateUrl:'views/home.html',
		controller:'UserController'
	})
	
})
app.run(function($rootScope,$cookieStore,UserService,$location){
		if($rootScope.currentUser==undefined)
			$rootScope.currentUser=$cookieStore.get("currentUser")
			
			$rootScope.logout=function(){
			UserService.logout().then(function(response){
				$rootScope.logoutSuccess="Loggedout Successfully.."
					delete $rootScope.currentUser
					$cookieStore.remove("currentUser")
				$location.path('/login');				
			},function(response){
				$scope.error=response.data
				console.log(response.status)
				$location.path('/login')				
			})
		}
	})
