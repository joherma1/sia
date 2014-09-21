<html>
<head>
    <TITLE>Ajax Test</TITLE>
</head>

<body>
<div>
    <br> <br> ${message} <br> <br>
    <b id="sensorId">01231412</b>
    <label>Response</label>
    <div id="result"></div>
    <br>
    <p>
    </p>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- Jquery CDN google -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- Sia javascripts for sensors -->
<script src="${pageContext.request.contextPath}/resources/js/sensors.js"></script>
</body>
</html>