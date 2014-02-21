<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>SIA</title>
	<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]--> <style>header, footer, section, aside, nav, article {display: block;}</style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fields.css"  >
</head>
<body>
	<nav>
		<ul>
			<li><a href="profile.html">Home</a></li>
			<li><a href="signout.html">Sign out</a></li>
		</ul>
	</nav>
	<header>
		<h1>Parcelas</h1>
	</header>
    <div class="fieldContainer">
        <div class="fieldList">
            <h3>Listado</h3>
            <table>
                <c:forEach items="${fields}" var="field">
                    <tr>
                        <td>${field.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="fieldDetail">
            <h3>Campos</h3>
        </div>
    </div>
	<footer>
		<p>joherma1@gmail.com</p>
	</footer>
</body>
</html>