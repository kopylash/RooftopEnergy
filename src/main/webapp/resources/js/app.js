//$(function(){
//
//    $("#sub").click(function(){
//        alert("Hello!!!");
//    });

    var app = angular.module('passwordChange', []);


    app.controller('passwordController', function($scope, $http){
        $scope.showRightBanner = false;
        $scope.showWrongBanner = false;

        $scope.userOldEnterPassword = "";
        $scope.userFirstNewPassword = "";
        $scope.userSecondNewPassword = "";


        $scope.showFalseOld = false;
        $scope.showRightNew = false;
        $scope.showFalseNew = false;


        $scope.newInputDisabled = true;
        $scope.subButtonDisabled = true;


        $scope.comperingPasswordFunction = function(){
            if($scope.userOldEnterPassword !== ""){
                $scope.newInputDisabled = false;
                $(".field div").removeClass("ui-state-disabled");
            }
        };

        $scope.comperingNewPassword = function(userFirstNewPassword, userSecondNewPassword){

            $scope.userFirstNewPassword = userFirstNewPassword;
            $scope.userSecondNewPassword = userSecondNewPassword;
            if(($scope.userFirstNewPassword) || ($scope.userSecondNewPassword)){
                if (($scope.userFirstNewPassword == $scope.userSecondNewPassword)) {
                    $scope.subButtonDisabled = false;
                    $scope.showRightNew = true;
                } else {
                    $scope.showFalseNew = true;
                    $scope.showRightNew = false;
                    $scope.subButtonDisabled = true;
                }
            } else {
                $scope.showFalseNew = false;
                $scope.showRightNew = false;
                $scope.subButtonDisabled = true;
            }
        };

        $scope.resFields = function(){
            $scope.userOldEnterPassword = "";
            $scope.userFirstNewPassword = "";
            $scope.userSecondNewPassword = "";
            $scope.showFalseOld = false;
            $scope.showRightNew = false;
            $scope.showFalseNew = false;
            $scope.newInputDisabled = true;
            $scope.subButtonDisabled = true;
            $scope.showRightBanner = false;
            $scope.showWrongBanner = false;
        };

        $scope.subFields = function(oldPass, newPass){
            var passwordTransfer ={
                newPassword: newPass,
                oldPassword: oldPass
            };
            console.log("IN Function!!!");
            console.log(newPass);
            console.log(oldPass);

            $http.post('/rest/boxData/changePassword', passwordTransfer).
                success(function() {
                    $scope.showRightBanner = true;
                    $scope.showWrongBanner = false;
                    console.log("GOOD!!!");
                }).
                error(function() {
                    $scope.showRightBanner = false;
                    $scope.showWrongBanner = true;
                    $scope.showFalseOld = true;
                    console.log("BAD!!!");
                });
        };

    });


//});
