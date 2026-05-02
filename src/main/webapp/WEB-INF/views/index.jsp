<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Manager</title>
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
    <div class="hero">
        <h2>Welcome to Library Manager</h2>
        <p>This app lets you browse and manage books and authors, an assignment by Kuber Mehta for SGA 2.</p>
        <div class="hero-links">
            <a href="${pageContext.request.contextPath}/books" class="btn btn-primary">View Books</a>
            <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">View Authors</a>
        </div>
    </div>
</div>
</body>
</html>
