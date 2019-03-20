app.controller('baseController',function($http,$scope){	
		$scope.paginationConf = {
    				 currentPage: 1,//当前页
    				 totalItems: 10,//总记录数
    				 itemsPerPage: 10,//每页记录数
    				 perPageOptions: [10, 20, 30, 40, 50],
    				 onChange: function(){
    				       $scope.reloadList();//重新加载
    				 }
    		}; 
		
		
		$scope.reloadList = function(){
			 $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
		 }
		
		
		$scope.selectIds = [];
		
		$scope.updateSelection = function($event,id){
			//获取下标
			var index = $event.$index;
			if($event.target.checked){//被选中,添加到数组中
				$scope.selectIds.push(id);
				
			}else{
				var ids = $scope.selectIds.indexOf(id);
				$scope.selectIds.splice(ids,1);
			}
		}
});