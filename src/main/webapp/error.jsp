<%--
  Created by IntelliJ IDEA.
  User: Heidi
  Date: 05/01/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%--the following code was copied and pasted from Michelle's solution and should be changed--%>
<h1>Something went wrong</h1>
<%
  // Get the error message variable out of the session
  Object msg = session.getAttribute("errorMessage");
  // If there is an error message to print
  if(msg != null){
    // Cast it to a String so we can use it
    String error = (String) msg;
    // Display the message
%>

<div> <%=error%> </div>
<%
    // We have finished with the results of this action
    // so now we can remove the value from the session
    session.removeAttribute("errorMessage");
    // This makes sure that old error messages
    // don't mistakenly get printed out later
  }
%>

<div><a href="../index.jsp">Back to Home</a></div>
</body>
</html>
