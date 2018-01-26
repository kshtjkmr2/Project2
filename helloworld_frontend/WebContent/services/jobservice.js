/**
 * jobService
 */
app.factory('JobService',function($http){
	var jobService={}
	var BASE_URL="http://localhost:9002/helloworld_backend"
		jobService.saveJob=function(job){
		return $http.post(BASE_URL+"/savejob",job)
	}
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL+"/getalljobs")
	}
	jobService.getJobDetails=function(id){
		return $http.get(BASE_URL+"/getjobbyid/"+id)
	}
	return jobService;
})