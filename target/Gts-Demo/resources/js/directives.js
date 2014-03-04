'use strict';

/* Directives */

var AppDirectives = angular.module('GtsDemoApp.directives', []);

AppDirectives.directive('appVersion', [ 'version', function(version) {
	return function(scope, elm, attrs) {
		elm.text(version);
	};
} ]);

AppDirectives.directive('ngFocus', [ function() {
	alert();
	var FOCUS_CLASS = "ng-focused";
	return {
		restrict : 'A',
		require : 'ngModel',
		link : function(scope, element, attrs, ctrl) {
			ctrl.$focused = false;
			element.bind('focus', function(evt) {
				element.addClass(FOCUS_CLASS);
				scope.$apply(function() {
					ctrl.$focused = false;
				});
			}).bind('blur', function(evt) {
				element.removeClass(FOCUS_CLASS);
				alert(ctrl.$focused);
				scope.$apply(function() {
					ctrl.$focused = false;
				});
			});
		}
	}
} ]);
AppDirectives.directive('ensureUnique', [ '$http', function($http) {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, ngModel) {
			var fldName = element.attr(ngModel.$name);
			console.log('wiring up ' + attrs.ngModel + ' to controller ' + ngModel.$name+'--'+element.val()+'<><>'+fldName);
			scope.$watch(attrs.ngModel, function() {
				if(element.val()!='')
				$http.post('companies/checkCompanyId/'+element.val()).success(function() {
		        }).success(function(data, status, headers, cfg) {
		        	console.log('--->>'+data);
		        	if(data=='true')
		        	ngModel.$setValidity('unique', true);
		        	else
		        		ngModel.$setValidity('unique', false);
				}).error(function(data, status, headers, cfg) {
					ngModel.$setValidity('unique', false);
				});
			});
		}
	}
} ]);

