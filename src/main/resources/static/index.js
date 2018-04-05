
/*
var model = {
    items: [
        { id: 10, name: "Эксковаторщик", salary: 10, requiredWorkExperience: "111111111111111111", city: "111111" },
        { id: 20, name: "Бульдозерист", salary: 10, requiredWorkExperience: "2222222222222222222", city: "222222" },
        { id: 30, name: "Разнорабочий", salary: 10, requiredWorkExperience: "33333333333333333", city: "333333" },
        { id: 40, name: "Прораб", salary: 10, requiredWorkExperience: "444444444444444444", city: "4444444" }
    ]
};
*/

var vacancyApp = angular.module("vacancyApp", []);
vacancyApp.controller("vacancyController", function ($scope, $http) {
    //$scope.list = model;

        $scope.mainUrlPort ='8082';
        $scope.mainUrl ='http://localhost:' + $scope.mainUrlPort + '/altatest';

        $scope.emptyVacancy = {id: "", name: "", salary: "", requiredWorkExperience: "", city: ""};
        $scope.editVacancy = angular.copy($scope.emptyVacancy);

        $scope.editMode = false;


        // обновить список
        $http.get($scope.mainUrl + '/vacancies').
            then(function(response) {

            	$scope.list = { "items" : response.data};
             }, function error(response){
                console.log('error');
                alert("Не получилось обновить список, проверьте правильность работы сервера");
             }
        );




        // добавление
        $scope.addItem = function () {

            salary = parseFloat($scope.editVacancy.salary); // преобразуем введенное значение к числу
            if(($scope.editVacancy.name != "" && $scope.editVacancy.name != undefined)
                && !isNaN(salary)
                && ($scope.editVacancy.requiredWorkExperience != "" && $scope.editVacancy.requiredWorkExperience != undefined)
                && ($scope.editVacancy.city != "" && $scope.editVacancy.city != undefined) )
            {
                var parameter = JSON.stringify({id: $scope.editVacancy.id, name: $scope.editVacancy.name, salary: salary, requiredWorkExperience:$scope.editVacancy.requiredWorkExperience, city: $scope.editVacancy.city});
                $http.put($scope.mainUrl + '/vacancy', parameter).
                    then(function(response){
                        $scope.editVacancy = angular.copy($scope.emptyVacancy);

                        $http.get($scope.mainUrl + '/vacancies').
                        then(function(response) {
                            $scope.list = { "items" : response.data};
                        });

                    }, function error(response) {

                        console.log(response.data);
                    }
                );

            } else {
                alert("Не все данные корректно введены.");
            }

        }

        // режим редактирования включить
        $scope.updateModeOn = function (inIdStr) {
            inId = parseFloat(inIdStr); // преобразуем введенное значение к числу
            if( !isNaN(inId))
            {

                // получить вакансию
                $http.get($scope.mainUrl + '/vacancy/'+inIdStr).
                then(function(response) {
                        $scope.editVacancy = response.data;
                        $scope.editMode = true;
                    }, function error(response){
                        console.log('error');
                        alert("Не получилось получить данные, проверьте правильность работы сервера");
                    }
                );
            }
        }


        // режима редактирования включить
        $scope.updateModeOff = function () {
            $scope.editMode = false
            $scope.editEmployee = angular.copy($scope.emptyEmployee);
        }

        // редактировать- обновить
        $scope.updateItem = function () {
            salary = parseFloat($scope.editVacancy.salary); // преобразуем введенное значение к числу
            if(($scope.editVacancy.name != "" && $scope.editVacancy.name != undefined)
                && !isNaN(salary)
                && ($scope.editVacancy.requiredWorkExperience != "" && $scope.editVacancy.requiredWorkExperience != undefined)
                && ($scope.editVacancy.city != "" && $scope.editVacancy.city != undefined) )
            {
                var parameter = JSON.stringify({
                    id: $scope.editVacancy.id,
                    name: $scope.editVacancy.name,
                    salary: $scope.editVacancy.salary,
                    requiredWorkExperience: $scope.editVacancy.requiredWorkExperience,
                    city: $scope.editVacancy.city
                });
                $http.post($scope.mainUrl + '/vacancy', parameter).then(function (response) {
                        $scope.editMode = false
                        $scope.editVacancy = angular.copy($scope.emptyVacancy);


                        // получить весь список
                        $http.get($scope.mainUrl + '/vacancies').then(function (response) {
                            $scope.list = {"items": response.data};
                        });

                    }, function error(response) {

                        console.log(response.data);
                    }
                );
            } else {
                alert("Не все данные корректно введены.");
            }

        }


        // удаление
        $scope.deleteItem = function (inIdStr) {
            inId = parseFloat(inIdStr); // преобразуем введенное значение к числу
            if( !isNaN(inId))
            {
                var parameter = "";
                $http.delete($scope.mainUrl + '/vacancy/'+inIdStr, parameter).
                then (function(response){
                    // получить весь список
                    $http.get($scope.mainUrl + '/vacancies/').
                    then(function(response) {
                        $scope.list = { "items" : response.data};
                    });

                }, function error(response){
                    console.log(response.data);
                });
            }
        }

});
	