<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books - Library Manager</title>
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
        <h2 class="page-title">Books <span class="badge">(fetched via inner join with authors)</span></h2>
        <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary">+ Add Book</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Year</th>
                <th>ISBN</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${books}" var="book" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${book.title}</td>
                    <td>${book.author.name}</td>
                    <td>${book.genre}</td>
                    <td>${book.publishedYear}</td>
                    <td>${book.isbn}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/books/edit/${book.id}" class="btn btn-warning">Edit</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty books}">
                <tr><td colspan="7" style="text-align:center;color:#9ca3af;padding:1.5rem;">No books found.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
