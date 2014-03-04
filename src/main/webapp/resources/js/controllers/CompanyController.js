'use strict';

var CompanyController = function($scope, $http) {
    $scope.company = {};
    $scope.editMode = false;

    $scope.fetchCompanyList = function() {
    	$http.get('companies/companieslist.json').success(function(companyList){
            $scope.companies = companyList;
        });
    	$scope.predicate = '-id';
    };

    
    $scope.addNewCompany = function(company) {
        $scope.resetError();

        $http.post('companies/addCompany', company).success(function() {
            $scope.fetchCompanyList();            
            $scope.company.companyId = '';
            $scope.company.companyName = '';
            $scope.company.contactPerson = '';
        }).error(function(data, status, headers, config) {
            $scope.setError('Could not add a new company');
        });
    };

    $scope.updateCompany = function(company) {
        $scope.resetError();

        $http.put('companies/updateCompany', company).success(function() {
            $scope.fetchCompanyList();
            $scope.company.companyId = '';
            $scope.company.companyName = '';
            $scope.company.contactPerson = '';
            $scope.editMode = false;
        }).error(function() {
            $scope.setError('Could not update the company');
        });
    };

    $scope.editCompany = function(company) {
        $scope.resetError();
        $scope.company = company;
        $scope.editMode = true;
    };

    $scope.removeCompany = function(id) {
        $scope.resetError();
        $http.delete('companies/removeCompany/' + id).success(function() {
            $scope.fetchCompanyList();
        }).error(function() {
            $scope.setError('Could not remove company');
        });
        $scope.company.name = '';
        $scope.company.speed = '';
    };

    $scope.removeAllCompany = function() {
        $scope.resetError();

        $http.delete('companies/removeAllCompany').success(function() {
            $scope.fetchCompanyList();
        }).error(function() {
            $scope.setError('Could not remove all companies');
        });

    };

    $scope.resetCompanyForm = function() {
        $scope.resetError();
        $scope.company = {};
        $scope.editMode = false;
    };

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchCompanyList();

    $scope.predicate = 'id';
};