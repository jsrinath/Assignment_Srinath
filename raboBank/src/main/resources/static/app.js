App.controller('docController',
    ['$scope', '$rootScope','$http', '$filter','NgTableParams', function($scope, $rootScope, $http, $filter,NgTableParams) {
    	var self = this;
        $scope.file = '';
        self.Personlist ={};
        self.variantFilterDef = {
        	    start: {
        	      id: 'number',
        	      placeholder: 'Start'
        	    },
        	    end: {
        	      id: 'number',
        	      placeholder: 'End'
        	    }
        	  };
        

        function variantRangeFilter (data, filterValues /*, comparator*/ ) {
            return data.filter(function(item) {
              var start = filterValues.start == null ? Number.MIN_VALUE : filterValues.start;
              var end = filterValues.end == null ? Number.MAX_VALUE : filterValues.end;
              return start <= item.issueCount && end >= item.issueCount;
            });
          };
        
        
        $scope.doUploadFile = function(){
            var file = $scope.fileArray[0]
            alert("Test"+file);
            var formData = new FormData();
            formData.append('file', file);

            $http.post('csvUpload', formData,{
                transformRequest : angular.identity,
                headers : {
                    'Content-Type' : undefined
                }})
                .then(
                    function (response) {
                    	alert(response.data[0].firstName)
                    	 self.Personlist =response.data;
                    	// $scope.tableParams = new NgTableParams({page: 1, count: 10}, {data:  $scope.Personlist});
                    	self.tableParams = new NgTableParams({
                    		    page: 1,
                    		    count: 10
                    		  }, {
                    		    filterOptions: {
                    		      filterFn: variantRangeFilter
                    		    },
                    		    total:  self.Personlist.length,
                    		    getData: function(params) {
                    		      if (params.sorting()) {
                    		        self.data = $filter('orderBy')(self.Personlist, params.orderBy());
                    		      } else {
                    		        self.data = self.Personlist;
                    		      }
                    		      if (params.filter()) {
                    		        self.data = $filter('filter')(self.data, {name: params.filter().name});
                    		        self.data = variantRangeFilter(self.data, params.filter());
                    		      } else {
                    		        self.data = self.data;
                    		      }
                    		      
                    		      self.data = self.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                    		      return self.data;
                    		    }
                    		  });
                    
                    },
                    function (errResponse) {
                        alert(errResponse.data.errorMessage);
                    }
                );
        }
        
    }
    ]);
