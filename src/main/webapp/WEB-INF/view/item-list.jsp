<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>List</title>
    </head>
    <body>
        <br>
         <h1>Item list</h1>
         <table>
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Cost</th>
                </tr>
                <c:forEach var="item" items="${Items}">
                    <tr>
                        <td>${item.getId()}</td>
                        <td>${item.getTitle()}</td>
                        <td>${item.getCost()}</td>
                    </tr>
                </c:forEach>
         </table>
    </body>
</html>
