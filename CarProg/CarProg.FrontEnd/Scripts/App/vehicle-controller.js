var mainApp = angular.module("mainApp", []);

mainApp.controller("vehicleCtrl", function ($scope, $http) {

    $scope.vehicles = [];
    $scope.vehicle = {};
    $scope.vehicle.id = 0;

    $scope.vehicleDelete = {};

    $scope.erros = [];
    $scope.errosShow = false;
    $scope.sucessoShow = false;

    var getAllVehicles = function () {
        $http({
            method: 'GET',
            url: '/v1/api/vehicle'
        }).then(function (response) {
            
            $scope.vehicles = response.data;

            console.log("Vehicles ", $scope.vehicles);

        }, function (error) {
            console.log("Falha ", error);
        });
    }

    var saveVehicle = function () {
        console.log("Vehicle ", $scope.vehicle.id);

        $scope.erros = [];
        $scope.errosShow = false;
        $scope.sucessoShow = false;

        if ($scope.vehicle.id == 0) {

            $http({
                url: '/v1/api/vehicle',
                method: "POST",
                data: JSON.stringify($scope.vehicle)

            }).then(function (response) {
                // success
                console.log("Sucesso ", response);
                $scope.sucessoShow = true;

                $scope.vehicle = {};
                $scope.vehicle.id = 0;

                getAllVehicles();
            },
                function (error) {

                    console.log("Error ", error);

                    if (error.data.exceptionMessage)
                        $scope.erros.push(error.data.exceptionMessage);
                    else if (error.data.message)
                        $scope.erros.push(error.data.message);

                    $scope.errosShow = true;
                });

        } else {

            $http({
                url: '/v1/api/vehicle',
                method: "PUT",
                data: JSON.stringify($scope.vehicle)

            }).then(function (response) {
                // success
                console.log("Sucesso ", response);
                $scope.sucessoShow = true;

                $scope.vehicle = {};

                getAllVehicles();
            },
                function (error) {

                    console.log("Error ", error);

                    if (error.data.exceptionMessage)
                        $scope.erros.push(error.data.exceptionMessage);
                    else if (error.data.message)
                        $scope.erros.push(error.data.message);

                    $scope.errosShow = true;
                });

        }
    }

    $scope.save = function () {
        saveVehicle();
    }

    $scope.edit = function (vehicle) {
        $scope.vehicle = {};
        $scope.vehicle.id = 0;

        console.log("Editar ", vehicle);

        $scope.vehicle = angular.fromJson(vehicle);
    }

    $scope.confirmDelete = function (vehicle) {
        $scope.vehicleDelete = {};

        $scope.vehicleDelete = angular.fromJson(vehicle);
    }

    $scope.delete = function (_id) {
        $scope.vehicleDelete = {};

        console.log("Excluir ", _id);

        $http({
            url: '/v1/api/vehicle',
            method: "DELETE",
            params: {
                id: _id
            }

        }).then(function (response) {
            // success
            console.log("Sucesso ", response);
            $scope.sucessoShow = true;
            
            getAllVehicles();
        },
            function (error) {

                console.log("Error ", error);

                if (error.data.exceptionMessage)
                    $scope.erros.push(error.data.exceptionMessage);
                else if (error.data.message)
                    $scope.erros.push(error.data.message);

                $scope.errosShow = true;

                getAllVehicles();
            });
    }

    var contrutor = function () {
        getAllVehicles();
    }

    contrutor();
});