//$(function(){
//
    $("#sub").click(function(){
        alert("Hello!!!");
    });

    var app = angular.module('passwordChange', []);


    app.controller('passwordController', function($scope){
        $scope.userOldPassword = "energy";
        $scope.userOldEnterPassword = "";
        $scope.userFirstNewPassword = "";
        $scope.userSecondNewPassword = "";


        $scope.showRightOld = false;
        $scope.showFalseOld = false;
        $scope.showRightNew = false;
        $scope.showFalseNew = false;


        $scope.newInputDisabled = true;
        $scope.subButtonDisabled = true;


        $scope.comperingPasswordFunction = function(){
            if($scope.userOldEnterPassword == $scope.userOldPassword){
                $scope.showRightOld = true;
                $scope.newInputDisabled = false;
            } else{
                $scope.showFalseOld = false;
            }
        };

    });


//});
