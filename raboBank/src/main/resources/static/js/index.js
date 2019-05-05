//variant part: routing, servicing, controlling
/*angular.module('variantApp', ['ngTable'])
*/
//controlling - this includes service and ng-table framework
App.controller('variant_table_controller', function(  $scope,$http, $filter, NgTableParams) {

  var self = this;
  $scope.file = '';
  self.Personlist ={};
  self.sampledata = [{
    name: "Moroni50",
    pos: 15
  }, {
    name: "Moroni49",
    pos: 20
  }, {
    name: "Moroni48",
    pos: 25
  }, {
    name: "Moroni47",
    pos: 35
  }, {
    name: "Moroni46",
    pos: 45
  }];
  self.variants = self.sampledata;

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
      return start <= item.pos && end >= item.pos;
    });
  };

  function rangeFilterIssueCnt (data, filterValues /*, comparator*/ ) {
	    return data.filter(function(item) {
	      var start = filterValues.start == null ? Number.MIN_VALUE : filterValues.start;
	      var end = filterValues.end == null ? Number.MAX_VALUE : filterValues.end;
	      return start <= item.issueCount && end >= item.issueCount;
	    });
	  };
  
 /* self.variantsTable = new NgTableParams({
    page: 1,
    count: 10
  }, {
    filterOptions: {
      filterFn: variantRangeFilter
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
        self.data = variantRangeFilter(self.data, params.filter());
      } else {
        self.data = self.data;
      }
      self.data = self.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
      return self.data;
    }
  });*/

  
  $scope.doUploadFile = function(){
      var file = $scope.fileArray[0];
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
                  alert(errResponse.data.errorMessage);
              }
          );
  }
  
  
});