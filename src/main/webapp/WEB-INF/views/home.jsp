<%@ page import="java.util.Date" %>
<html>
<head><title>SIA Home</title></head>
<body>
Welcome to SIA <%= new Date()%> <br>
Java Version <%= System.getProperty("java.version")%> <br>
<br>
<%
    // This scriptlet declares and initializes "date"
    System.out.println( "Evaluating date now" );
    java.util.Date date = new java.util.Date();
%>
Hello!  The time is now
<%
    // This scriptlet generates HTML output
    out.println( String.valueOf( date ));
    out.println( "<BR>Your machine's address is " );
    out.println( request.getRemoteHost());
%>
</body>
</html>
