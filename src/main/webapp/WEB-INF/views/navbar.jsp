<%--@elvariable id="user" type="org.sysreg.sia.model.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li><a href="<c:url value="/dashboard"/>">Dashboard</a></li>
                <li><a href="<c:url value="/sensors"/>">Sensors</a></li>
                <li><a href="<c:url value="/actuators"/>">Actuators</a></li>
                <li><a href="<c:url value="/alarms"/>">Alarms</a></li>
                <li><a href="<c:url value="/issues"/>">Issues</a></li>
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
                        <li><a href="<c:url value=" j_spring_security_logout" />" > Logout</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>