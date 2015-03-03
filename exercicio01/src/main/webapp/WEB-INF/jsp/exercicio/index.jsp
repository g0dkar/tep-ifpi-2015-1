<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html ng-app="tepEx1">
<head>
<meta charset="utf-8">
<title>TEP - Exercício 01</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600">
<link rel="stylesheet" href="https://rawgit.com/angular/bower-material/master/angular-material.css">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-animate.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.6/angular-aria.js"></script>
<script src="https://rawgit.com/angular/bower-material/master/angular-material.js"></script>
</head>
<body layout="column" ng-controller="MainController">
	<md-toolbar layout="row">
		<button ng-click="toggleSidenav('left')" hide-gt-sm class="menuBtn">
			<span class="visually-hidden">Menu</span>
		</button>
		<h1>TEP - Exercício 01</h1>
	</md-toolbar>
	<div layout="row" flex>
		<md-sidenav layout="column" class="md-sidenav-left md-whiteframe-z2" md-component-id="left" md-is-locked-open="$media('gt-sm')">
			
		</md-sidenav>
		<div layout="column" flex id="content">
			<md-content layout="column" flex class="md-padding"> </md-content>
		</div>
	</div>
</body>
</html>