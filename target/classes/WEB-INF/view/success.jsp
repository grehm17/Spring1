<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <h2>Successfully added!<h2>
        <form:form action="addItem" modelAttribute="Item">
            <input type="submit" value="Return" />
        </form:form>
    </body>
</html>
