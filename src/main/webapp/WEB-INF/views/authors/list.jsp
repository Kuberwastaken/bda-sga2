<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authors - Library Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <h1>Library Manager</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/">Home</a>
        <a href="${pageContext.request.contextPath}/books">Books</a>
        <a href="${pageContext.request.contextPath}/authors">Authors</a>
    </nav>
</header>
<div class="container">
    <div class="actions-bar">
        <h2 class="page-title">Authors</h2>
        <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary">+ Add Author</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Nationality</th>
                <th>Birth Year</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${authors}" var="author" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${author.name}</td>
                    <td>${author.nationality}</td>
                    <td>${author.birthYear}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/authors/edit/${author.id}" class="btn btn-warning">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty authors}">
                <tr><td colspan="5" style="text-align:center;color:#9ca3af;padding:1.5rem;">No authors found.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
