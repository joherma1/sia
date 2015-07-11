<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="enclosure" type="org.sysreg.sia.model.Enclosure"--%>

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

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
</head>

<body role="document">
<jsp:include page="navbar.jsp"/>
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
                        <div class="col-sm-7">
                            <img src="${pageContext.request.contextPath}/resources/images/Field_1.png"
                                 class="img-rounded img-responsive"
                                 style="margin: 0 auto; padding-top: 50px;">
                        </div>
                        <div class="col-sm-5">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h2 class="panel-title">Sensors</h2>
                                </div>
                                <div class="panel-body table">
                                    <table class="table table-striped table-condensed sensor-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Value</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${enclosure.boards}" var="board">
                                            <c:forEach items="${board.sensors}" var="sensor">
                                                <tr>
                                                    <td><c:out value="${sensor.id}"/></td>
                                                    <td>
                                                    <span id="${sensor.id}-value">
                                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${sensor.value}" />
                                                    </span>
                                                    <span>
                                                        <c:choose>
                                                            <c:when test="${sensor.units.name() == 'CELSIUS'}">
                                                                &degC
                                                            </c:when>
                                                            <c:when test="${sensor.units.name() == 'FAHRENHEIT'}">
                                                                &degF
                                                            </c:when>
                                                            <c:when test="${sensor.units.name() == 'KELVIN'}">
                                                                &degK
                                                            </c:when>
                                                            <c:when test="${sensor.units.name() == 'PASCAL'}">
                                                                Pa
                                                            </c:when>
                                                            <c:when test="${sensor.units.name() == 'PERCENT'}">
                                                                %
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${sensor.units.name()}"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </span>
                                                    </td>
                                                    <td>
                                                        <button type="button" id="sensor-refresh" value="${sensor.id}" class="btn btn-default btn-xs" aria-label="Refresh Sensor" onclick="checkAvailability('${sensor.id}')">
                                                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="panel-footer">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <button type="button" class="btn btn-default btn-xs" aria-label="Add Sensor">
                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" class="btn btn-default btn-xs" aria-label="Delete Sensor">
                                            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" class="btn btn-default btn-xs" aria-label="Edit Sensor">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h2 class="panel-title">Actuators</h2>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-condensed table-responsive actuator-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Description</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${enclosure.boards}" var="board">
                                            <c:forEach items="${board.actuators}" var="actuator">
                                                <tr>
                                                    <td><c:out value="${actuator.id}"/></td>
                                                    <td><c:out value="${actuator.description}"/></td>
                                                    <td>
                                                        <c:if test="${actuator.enabled == true}">
                                                            <button type="button" class="btn btn-default btn-xs active" data-toggle="button" aria-pressed="true" autocomplete="off">
                                                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${actuator.enabled == false}">
                                                            <button type="button" class="btn btn-default btn-xs" data-toggle="button" aria-pressed="false" autocomplete="off">
                                                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                                                            </button>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="panel-footer">
                                    <div class="btn-group" role="group" aria-label="Basic example">
                                        <button type="button" class="btn btn-default btn-xs" aria-label="Add Sensor">
                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" class="btn btn-default btn-xs" aria-label="Delete Sensor">
                                            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                        </button>
                                        <button type="button" class="btn btn-default btn-xs" aria-label="Edit Sensor">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </div>
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
                        <td>
                            <c:out value="${enclosure.parcel.town.name}"/></td>
                    </tr>
                    <tr>
                        <td><b>Region</b></td>
                        <td><c:out value="${enclosure.parcel.town.region.name}"/></td>
                    </tr>
                    <tr>
                        <td><b>Province</b></td>
                        <td><c:out value="${enclosure.parcel.town.region.province.name}"/></td>
                    </tr>
                    <tr>
                        <td><b>Aggregate</b></td>
                        <td><c:out value="${enclosure.parcel.aggregate}"/></td>
                    </tr>
                    <tr>
                        <td><b>Zone</b></td>
                        <td><c:out value="${enclosure.parcel.zone}"/></td>
                    </tr>
                    <tr>
                        <td><b>Polygon</b></td>
                        <td><c:out value="${enclosure.parcel.polygon}"/></td>
                    </tr>
                    <tr>
                        <td><b>Parcel</b></td>
                        <td><c:out value="${enclosure.parcel.parcel}"/></td>
                    </tr>
                    <tr>
                        <td><b>Enclosure</b></td>
                        <td><c:out value="${enclosure.enclosure}"/></td>
                    </tr>
                    <tr>
                        <td><b>Area</b></td>
                        <td><c:out value="${enclosure.area}"/></td>
                    </tr>
                    <tr>
                        <td><b>Slope</b></td>
                        <td><c:out value="${enclosure.slope}"/></td>
                    </tr>
                    <tr>
                        <td><b>Irrigation Coef</b></td>
                        <td><c:out value="${enclosure.irrigationCoef}"/></td>
                    </tr>
                    <tr>
                        <td><b>Coordinates</b></td>
                        <td><c:out value="${enclosure.coordinates}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Use</b></td>
                        <td><c:out value="${enclosure.use.description}"/></td>
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
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<!-- Sia javascripts for home -->
<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
</body>
</html>