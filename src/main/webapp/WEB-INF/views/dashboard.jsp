<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
</head>
<body>
<div id="topHeader">
    <div id="userInfo">
        <ul>
            <li>
                <sec:authentication property="principal.username"/>
            </li>
            <li>
                <a href="/logout">Logout</a>
            </li>
        </ul>
    </div>
</div>
<div id="menu">
    <ul>
        <li class="menuList">
            <a class="menuItem" href="dashboard">Dashboard</a>
        </li>
        <li class="menuList">
            <a class="menuItem" href="sensors">Sensors</a>
        </li>
        <li class="menuList">
            <a class="menuItem" href="actuators">Actuators</a>
        </li>
        <li class="menuList">
            <a class="menuItem" href="alarms">Alarms</a>
        </li>
        <li class="menuList">
            <a class="menuItem" href="issues">Issues</a>
        </li>
        <li class="menuList">
            <a class="menuItem" href="settings">Settings</a>
        </li>
    </ul>
</div>
<div id="mainContainer">
    <div id="map">
        <img src="${pageContext.request.contextPath}/resources/images/Field_1.png">
    </div>
    <div id="enclosure" >
        <h1> ${enclosure.use.description}</h1>
    </div>
    <div id="hardware">
        <div id="sensors">
            <%@include file="sensors.jsp"%>
        </div>
        <div id="actuators">
            <%@include file="actuators.jsp"%>
        </div>
    </div>
</div>


</body>
</html>