<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Author - Library Manager</title>
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
    <h2 class="page-title" style="margin-bottom:1.2rem;">Add Author</h2>
    <div class="card">
        <c:if test="${not empty error}">
            <div class="alert-error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/authors/add" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="${author.name}" required>
            </div>
            <div class="form-group">
                <label for="nationality">Nationality</label>
                <input type="text" id="nationality" name="nationality" value="${author.nationality}">
            </div>
            <div class="form-group">
                <label for="birthYear">Birth Year</label>
                <input type="number" id="birthYear" name="birthYear" value="${author.birthYear}" min="1000" max="2100">
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Save Author</button>
                <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
