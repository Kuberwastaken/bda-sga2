<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Book - Library Manager</title>
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
    <h2 class="page-title" style="margin-bottom:1.2rem;">Edit Book</h2>
    <div class="card">
        <c:if test="${not empty error}">
            <div class="alert-error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/books/edit/${book.id}" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" value="${book.title}" required>
            </div>
            <div class="form-group">
                <label for="authorId">Author</label>
                <select id="authorId" name="authorId" required>
                    <c:forEach items="${authors}" var="a">
                        <option value="${a.id}" <c:if test="${book.author != null && book.author.id == a.id}">selected</c:if>>${a.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="genre">Genre</label>
                <input type="text" id="genre" name="genre" value="${book.genre}">
            </div>
            <div class="form-group">
                <label for="publishedYear">Published Year</label>
                <input type="number" id="publishedYear" name="publishedYear" value="${book.publishedYear}" min="1000" max="2100">
            </div>
            <div class="form-group">
                <label for="isbn">ISBN</label>
                <input type="text" id="isbn" name="isbn" value="${book.isbn}">
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Update Book</button>
                <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
