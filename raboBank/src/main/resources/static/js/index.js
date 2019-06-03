//variant part: routing, servicing, controlling
/*angular.module('variantApp', ['ngTable'])
*/
//controlling - this includes service and ng-table framework
App.controller('variant_table_controller', function(  $scope,$http, $filter, NgTableParams) {

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


  function rangeFilterIssueCnt (data, filterValues /*, comparator*/ ) {
	    return data.filter(function(item) {
	      var start = filterValues.start == null ? Number.MIN_VALUE : filterValues.start;
	      var end = filterValues.end == null ? Number.MAX_VALUE : filterValues.end;
	      return start <= item.issueCount && end >= item.issueCount;
	    });
	  };
  
  
  $scope.doUploadFile = function(){
      var file = $scope.fileArray[0];
      var formData = new FormData();
      formData.append('file', file);
      $http.post('csvUpload', formData,{
          transformRequest : angular.identity,
          headers : {
              'Content-Type' : undefined
          }})
          .then(
              function (response) {
              	 self.Personlist =response.data;
              	self.variants  = response.data;
                self.personTable = new NgTableParams({
                  page: 1,
                  count: 10
                }, {
                  filterOptions: {
                    filterFn: rangeFilterIssueCnt
                  },
                  total: self.variants.length,
                  getData: function(params) {
                    if (params.sorting()) {
                      self.data = $filter('orderBy')(self.variants, params.orderBy());
                    } else {
                      self.data = self.variants;
                    }
                    if (params.filter()) {
                      self.data = $filter('filter')(self.data, {name: params.filter().name});
                      self.data = rangeFilterIssueCnt(self.data, params.filter());
                    } else {
                      self.data = self.data;
                    }
                    self.data = self.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                    return self.data;
                  }
                });
              	
              	// $scope.tableParams = new NgTableParams({page: 1, count: 10}, {data:  $scope.Personlist});
              
              },
              function (errResponse) {
                //  alert(errResponse.data.message);
              }
          );
  }
  
  
});