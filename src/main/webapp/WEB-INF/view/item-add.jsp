<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <form:form action="writeItem" modelAttribute="Item">
            Id: <form:input path="Id" />
            <br>
            Title: <form:input path="Title" />
            <br>
            <br>
            Cost: <form:input path="Cost" />
            <br>
            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>
