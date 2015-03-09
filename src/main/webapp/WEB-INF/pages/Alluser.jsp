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
                        </tr>
                    </condition:forEach>
                    </tbody>
                </table>
        </condition:if>
        <condition:if test="${empty customers}">
           <h6>There is no customers in a database!</h6>
        </condition:if>
    </div>

    <footer>
        Copyright 2015 Raphael Zielinski
    </footer>
</body>
</html>