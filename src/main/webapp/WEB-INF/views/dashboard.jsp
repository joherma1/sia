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
    <link rel="shortcut icon" type="image/x-icon" href="/favicon.ico"/>

    <title>Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- Bootstrap theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
</head>

<body role="document">
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
                        <li><a href="<c:url value="j_spring_security_logout" />"> Logout</a></li>

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
    <div class="row">
        <div class="col-sm-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Main</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-8">
                            <img src="${pageContext.request.contextPath}/resources/images/Field_1.png"
                                 class="img-rounded img-responsive"
                                 style="margin: 0 auto; padding-top: 50px;">
                        </div>
                        <div class="col-sm-4">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h2 class="panel-title">Sensors</h2>
                                </div>
                                <div class="panel-body table">
                                    <table class="table table-striped table-condensed">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Value</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Id 1</td>
                                            <td>Sensor 1</td>
                                            <td>Value 1</td>
                                        </tr>
                                        <tr>
                                            <td>Id 2</td>
                                            <td>Sensor 2</td>
                                            <td>Value 2</td>
                                        </tr>
                                        <tr>
                                            <td>Id 3</td>
                                            <td>Sensor 3</td>
                                            <td>Value 3</td>
                                        </tr>
                                        <tr>
                                            <td>Id 4</td>
                                            <td>Sensor 4</td>
                                            <td>Value 4</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h2 class="panel-title">Actuators</h2>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-condensed table-responsive">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Value</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>Id 1</td>
                                            <td>Sensor 1</td>
                                            <td>Value 1</td>
                                        </tr>
                                        <tr>
                                            <td>Id 2</td>
                                            <td>Sensor 2</td>
                                            <td>Value 2</td>
                                        </tr>
                                        <tr>
                                            <td>Id 3</td>
                                            <td>Sensor 3</td>
                                            <td>Value 3</td>
                                        </tr>
                                        <tr>
                                            <td>Id 4</td>
                                            <td>Sensor 4</td>
                                            <td>Value 4</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Information</h3>
                </div>
                <table class="table">
                    <tbody>
                    <tr>
                        <td><b>Town</b></td>
                        <td><c:out value="${enclosure.parcel.town.name}" /></td>
                    </tr>
                    <tr>
                        <td><b>Region</b></td>
                        <td><c:out value="${enclosure.parcel.town.region.name}" /></td>
                    </tr>
                    <tr>
                        <td><b>Province</b></td>
                        <td><c:out value="${enclosure.parcel.town.region.province.name}" /></td>
                    </tr>
                    <tr>
                        <td><b>Aggregate</b></td>
                        <td><c:out value="${enclosure.parcel.aggregate}" /></td>
                    </tr>
                    <tr>
                        <td><b>Zone</b></td>
                        <td><c:out value="${enclosure.parcel.zone}" /></td>
                    </tr>
                    <tr>
                        <td><b>Polygon</b></td>
                        <td><c:out value="${enclosure.parcel.polygon}" /></td>
                    </tr>
                    <tr>
                        <td><b>Parcel</b></td>
                        <td><c:out value="${enclosure.parcel.parcel}" /></td>
                    </tr>
                    <tr>
                        <td><b>Enclosure</b></td>
                        <td><c:out value="${enclosure.enclosure}" /></td>
                    </tr>
                    <tr>
                        <td><b>Area</b></td>
                        <td><c:out value="${enclosure.area}" /></td>
                    </tr>
                    <tr>
                        <td><b>Slope</b></td>
                        <td><c:out value="${enclosure.slope}" /></td>
                    </tr>
                    <tr>
                        <td><b>Irrigation Coef</b></td>
                        <td><c:out value="${enclosure.irrigationCoef}" /></td>
                    </tr>
                    <tr>
                        <td><b>Coordinates</b></td>
                        <td><c:out value="${enclosure.coordinates.x}" />:<c:out value="${enclosure.coordinates.y}" />:<c:out value="${enclosure.coordinates.datum}" />:<c:out value="${enclosure.coordinates.spindle}" />
                        </td>
                    </tr>
                    <tr>
                        <td><b>Use</b></td>
                        <td><c:out value="${enclosure.use.description}" /></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-warning">
                <div class="panel-heading">
                    <h3 class="panel-title">Log</h3>
                </div>
                <div class="panel-body">
                    <div class="alert alert-success" role="alert">
                        <strong>Well done!</strong> You successfully read this important alert message.
                    </div>
                    <div class="alert alert-info" role="alert">
                        <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
                    </div>
                    <div class="alert alert-warning" role="alert">
                        <strong>Warning!</strong> Best check yo self, you're not looking too good.
                    </div>
                    <div class="alert alert-danger" role="alert">
                        <strong>Oh snap!</strong> Change a few things up and try submitting again.
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>