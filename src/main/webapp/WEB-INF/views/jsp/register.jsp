<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Register</title>
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
<form:form method="post" commandName="register">
    <%--<form:errors path="*" cssClass="errorblock" element="div" />--%>
    USERNAME:<input type="text" name="name" id="username">
    <form:errors path="name" cssClass="error" />
    PASSWORD:<input type="text" name="pass" id="password">

    <input type="submit" value="submit">
</form:form>
</body>
</html>
