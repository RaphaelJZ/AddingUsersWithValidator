<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="condition" %>
<html>
<body>
<link rel='stylesheet' href='/resources/css/style.css' type='text/css'>
&nbsp;
&nbsp;
&nbsp;
    <h4>System Error -please contact Your webmaster</h4>


<condition:if test="${not empty errMsg}">
    <h5>${errMsg}</h5>
</condition:if>


</body>
</html>