<%@ page import="org.sysreg.sia.model.Field" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>SIA</title>
	<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]--> <style>header, footer, section, aside, nav, article {display: block;}</style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fields.css"  >
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
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
            <p>
                <span>Selected: </span><span id="idSelected">none</span>
            </p>
            <table id="fieldsSelectable">
                <c:forEach items="${fields}" var="field">
                    <tr>
                        <td>${field.name}
                        <%
                            Field field= (Field) pageContext.getAttribute("field");
                            out.print(field.toString());
                        %>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <script>
                $(function() {
                    $( "#fieldsSelectable" ).selectable({
                        stop: function() {
                            var result = $( "#idSelected" ).empty();
                            $( ".ui-selected", this ).each(function() {
                                var index = $( "#fieldsSelectable li" ).index( this );
                                result.append( " #" + ( index + 1 ) );
                            });
                        },
                        selected: function(event, ui){
                            console.log($(ui.selected));
                        }
                    });
                });
            </script>
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