<!doctype html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="condition" %>


<html>
<head>
    <title>Customers</title>
    <link rel='stylesheet' href='/resources/css/style.css' type='text/css'>

</head>

<body>
   <div id="container">
    <header>
        <h1>Spring MVC App</h1>
    </header><!--header end-->

    <nav id="menu">
        <ul>
            <li class="menuitem"><a href="/">Home</a></li>
            <li class="menuitem"><a href="allCustomersJSON">All customers JSON</a></li>
            <li class="menuitem"><a href="Allusers">All customers</a></li>
            <li class="menuitem"><a href="Contact">Contact Us</a></li>
        </ul>
    </nav><!--menu end-->

    <h1>Customer:</h1>
    <form:form method="post" action="add" commandName="customer" >
       <table >
        <tr>
            <td><form:label  path="name" ><h2>Name:</h2></form:label></td>
            <td><form:input path="name"  size="30" /></td>
            <td> <form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="email"><h2>Email:</h2></form:label></td>
            <td><form:input path="email"  size="30" /></td>
            <td> <form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="telephone"><h2>Telephone:</h2></form:label></td>
            <td><form:input path="telephone" size="30" /></td>
            <td> <form:errors path="telephone" cssClass="error"/></td>
        </tr>
        <tr>
            <td><h3>Address:</h3></td>
        </tr>
        <tr>
            <td><form:label path="street"><h2>Street:</h2></form:label></td>
            <td><form:input path="street"  size="30"/></td>
            <td> <form:errors path="street" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="city"><h2>City:</h2></form:label></td>
            <td><form:input path="city"  size="30"/></td>
            <td> <form:errors path="city" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="zip"><h2>Zip code:</h2></form:label></td>
            <td><form:input path="zip"  size="30"/></td>
            <td> <form:errors path="zip" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="state"><h2>State:</h2></form:label></td>
            <td><form:select path="state">
                <form:option value="" label="...." />
                <form:options items="${states}" />
            </form:select>
            </td>
            <td> <form:errors path="state" cssClass="error"/></td>
        </tr>
        <tr>
            <td><input type="submit"  class="addButton" value="Submit"/></td>

        </tr>
      </table>
    </form:form>


         <condition:if test="${!empty customers}">

                <h1>Customers List:</h1>
                <table class="alltable">
                    <thead >
                        <tr class="alltable">
                            <th class="alltable">Id</th>
                             <th class="alltable">Name</th>
                             <th class="alltable">Email</th>
                             <th class="alltable">Telephone</th>
                             <th class="alltable">Street</th>
                             <th class="alltable">City</th>
                             <th class="alltable">Zip</th>
                             <th class="alltable">State</th>
                             <th class="alltable">&nbsp;</th>
                             <th class="alltable">&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                    <condition:forEach items="${customers}" var="customer">
                        <tr class="alltable">
                            <td class="alltable">${customer.id}</td>
                            <td class="alltable">${customer.name}</td>
                            <td class="alltable">${customer.email}</td>
                            <td class="alltable">${customer.telephone}</td>
                            <td class="alltable">${customer.street}</td>
                            <td class="alltable">${customer.city}</td>
                            <td class="alltable">${customer.zip}</td>
                            <td class="alltable">${customer.state}</td>
                            <td class="alltable">
                                <form action="edit/${customer.id}" method="put"><input type="submit" class="tableButton" value="Edit"/></form>
                            </td>
                            <td class="alltable">
                                <form action="delete/${customer.id}" method="delete"><input type="submit" class="tableButton" value="Delete"/></form>
                            </td>
                        </tr>
                    </condition:forEach>
                    </tbody>
                </table>
        </condition:if>

     </div>

    <footer>
    Copyright 2015 Raphael Zielinski
    </footer>
</body>
</html>