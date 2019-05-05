/*'use strict';
var App = angular.module('uploadApp',['ngTable']);
App.directive("selectNgFiles", function() {
	  return {
	    require: "ngModel",
	    link: function postLink(scope,elem,attrs,ngModel) {
	      elem.on("change", function(e) {
	        var files = elem[0].files;
	        ngModel.$setViewValue(files);
	      })
	    }
	  }
	});
App.factory('docService', ['$http', '$q', 'urls', function ($http, $q, urls) {

            var factory = {
                saveDoc: saveDoc,
            };

            return factory;

            function saveDoc(file) {
                var deferred = $q.defer();
                var formData = new FormData();
                formData.append('file', file);

                $http.post('csvUpload', formData,{
                    transformRequest : angular.identity,
                    headers : {
                        'Content-Type' : undefined
                    }})
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            alert(errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            };

        }
    ]);*/