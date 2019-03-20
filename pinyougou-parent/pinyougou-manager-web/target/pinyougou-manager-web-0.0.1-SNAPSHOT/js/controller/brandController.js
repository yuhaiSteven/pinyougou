app.controller('brandController',function($http,$controller,$scope,brandService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    		$scope.findAll = function(){
    			brandService.findAll().success(
	    				function(response){
	    					$scope.list = response;
	    				}
	    		);
    		}
    		
    		
    		
    		 

    		$scope.findByPage = function(pageNum,pageSize){
    			brandService.findByPage(pageNum,pageSize).success(
    					function(response){
    						$scope.paginationConf.totalItems = response.total;
    						$scope.list = response.rows;
    					}
    			);}
    		
    		$scope.save = function(){
    			
    			//获取entity对象
    			var entity = $scope.entity;
    			var method = brandService.add(entity);
    			if(entity.id !=null){
    				method = brandService.update(entity);
    			}
    			method.success(
    				function(response){
    					alert(response.message);
    					if(response.message){
    						$scope.search();//重新加载
    					}
    				}		
    			);
    		}
    		
    		
			$scope.findOne = function(id){
    			
				brandService.findOne(id).success(
    				function(response){
    					$scope.entity = response;
    				}		
    			);
    		}
			
			
			
			$scope.dele = function(){
				var selectIds = $scope.selectIds;
				brandService.dele(selectIds).success(
							function(response){
								alert(response.message);
								if(response.flag){
									$scope.selectIds = [];
									$scope.search();//重新加载
								}
				}
			);
			}
			//$scope.searchEntity=[];
			
			$scope.search = function(pageNum,pageSize){
				brandService.search(pageNum,pageSize,$scope.searchEntity).success(
    					function(response){
    						$scope.paginationConf.totalItems = response.total;
    						$scope.list = response.rows;
    					}
    			);}
    		
    		
    	});