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
<jsp:include page="navbar.jsp"/>
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
                        <c:out value="${field.description}"/>
                    </label>
                    <!-- Parcels for each field -->
                    <ul class="nav nav-list tree">
                        <c:forEach items="${field.parcels}" var="parcel">
                            <li>
                                <label class="tree-toggler nav-header">
                                    Parcela: <c:out value="${parcel.description}"/>
                                </label>
                                <ul class="nav nav-list tree">
                                    <c:forEach items="${parcel.enclosures}" var="enclosure">
                                        <li>
                                            <a href="<c:url value="/dashboard?enclosureId=${enclosure.id}" />">
                                                <c:out value="${enclosure.description}" />
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
