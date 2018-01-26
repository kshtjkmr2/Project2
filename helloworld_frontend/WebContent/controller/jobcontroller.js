/**
 * jobController
 */

app.controller('JobController',function(JobService,$scope,$location){
	$scope.showJobDetails=false;
	
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			$scope.jobs=response.data;
		},function(response){
			$location.path('/home')
		})
	}
	
	$scope.saveJob=function(){
		JobService.saveJob($scope.job).then(function(response){
			alert("Job Posted successfully")
			$location.path('/getalljobs')			
		},function(response){
			console.log(response.status)
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
			if(response.data==500){
				$scope.error=response.data
				$location.path=('/savejob')
			}
			console.log(response.status)
			alert("Please fill all attribute required")
			$location.path('/savejob')
		})
	}
	
	$scope.getJobDetails=function(id){
		$scope.showJobDetails=true
		JobService.getJobDetails(id).then(function(response){
			$scope.job=response.data
		},function(response){
			console.log(response.data)
			$location.path('/home')
		})
	}
	getAllJobs()
})