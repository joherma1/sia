<%@ page import="org.sysreg.sia.model.Field" %>
<%@ page import="org.sysreg.sia.model.Enclosure" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico" />

    <title>Homepage</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">SIA</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="/dashboard">Dashboard</a></li>
                <li><a href="/sensors">Sensors</a></li>
                <li><a href="/actuators">Actuators</a></li>
                <li><a href="/alarms">Alarms</a></li>
                <li><a href="/issues">Issues</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <img src="resources/images/user01.jpg" alt="" class="nav-user-pic img-responsive">
                            <%--<sec:authentication property="principal.username" />--%>
                            ${user.name}
                        <b class="caret"></b>
                    </a>
                    <!-- Dropdown menu -->
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Account</a></li>
                        <li class="divider"></li>
                        <li class="dropdown-header">Settings</li>
                        <li><a href="#">Fields</a></li>
                        <li><a href="#">Other</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout">Logout</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <ol class="breadcrumb">
        <li><a href="#">Home</a></li>
        <li><a href="#">Library</a></li>
        <li class="active">Data</li>
    </ol>
</div>

<div class="container">
    <div class="tree-group">
        <ul class="nav nav-list">
            <c:forEach items="${fields}" var="field">
                <li><label class="tree-toggler nav-header">
                        ${field.name}
                    </label>
                    <!-- Parcels for each field -->
                    <ul class="nav nav-list tree">
                        <c:forEach items="${field.parcels}" var="parcel">
                            <li>
                                <label class="tree-toggler nav-header">
                                    Parcela: ${parcel.town.name} - ${parcel.aggregate} - ${parcel.zone}
                                </label>
                                <ul class="nav nav-list tree">
                                    <c:forEach items="${parcel.enclosures}" var="enclosure">
                                        <li>
                                            <a href="#">
                                                <%
                                                    Enclosure enclosure = (Enclosure) pageContext.getAttribute("enclosure");
                                                    out.print(enclosure.toString());
                                                %>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                <li class="divider"></li>
            </c:forEach>
        </ul>
    </div>

    <div class="starter-template">
        <h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
    </div>

</div><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- Jquery CDN google -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- Sia javascripts for home -->
<script src="${pageContext.request.contextPath}/resources/js/home.js"></script>
</body>
</html>
