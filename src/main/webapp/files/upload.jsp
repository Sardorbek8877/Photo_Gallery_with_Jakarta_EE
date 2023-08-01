<%--
  Created by IntelliJ IDEA.
  User: sardo
  Date: 01.08.2023
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gallery</title>
</head>
<body>
<h1>Photo Gallery</h1>

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <input type="submit" value="Upload">
</form>

<hr>

<h2>Uploaded Images:</h2>
<c:forEach var="file" items="${requestScope.files}">
    <img src="${pageContext.request.contextPath}/images/${file}" alt="Image" width="300" height="200">
</c:forEach>

<p>${message}</p>
</body>
</html>
