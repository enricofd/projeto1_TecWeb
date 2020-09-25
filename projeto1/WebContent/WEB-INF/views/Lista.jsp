<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*,br.edu.insper.mvc.*" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Arquivos</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
    <form method="get" action="Lista">
        <div class="d-flex justify-content-center mx-5 my-3">
            <input type="text" class="form-control mr-3" name="search" placeholder="Search in files">
            <button type="submit" class="btn btn-outline-secondary" value="Save">Search</button>
        </div>

    </form>

    <h1 class="d-flex justify-content-center display-1 mt-3 mb-3">Files</h1>
    <div class="d-flex justify-content-center mt-3">
    </div>
    <div class="d-flex justify-content-center">
        <h5 class="mb-1 mt-3">Category filters:</h5>
    </div>
    <div class="d-flex justify-content-center mb-2">
        <form action="Lista" method="get">
            <input type='hidden' name='categorySent' value="NR">
            <button type="submit" class="btn btn-outline-info mx-1">sem filtros</button>
        </form>
        <c:forEach var="category" items="${categories}">
            <form action="Lista" method="get">
                <input type='hidden' name='categorySent' value="${category}">
                <button type="submit" class="btn btn-outline-info mx-1">${category}</button>
            </form>
        </c:forEach>
    </div>
    <div class="d-flex ml-5">
        <h5 class="mt-1">Order by:</h5>
        <form action="Lista" method="get">
            <input type='hidden' name='orderSent' value="NR">
            <button type="submit" class="btn btn-outline-primary mx-1">None</button>
        </form>
        <form action="Lista" method="get">
            <input type='hidden' name='orderSent' value="category">
            <button type="submit" class="btn btn-outline-primary mx-1">Category</button>
        </form>
        <form action="Lista" method="get">
            <input type='hidden' name='orderSent' value="user_id">
            <button type="submit" class="btn btn-outline-primary mx-1">User</button>
        </form>
        <form action="Lista" method="get">
            <input type='hidden' name='orderSent' value="date">
            <button type="submit" class="btn btn-outline-primary mx-1">Time</button>
        </form>
    </div>
    <div class="d-flex justify-content-center mx-5 mt-3">
        <table class="table table-hover ">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Category</th>
                    <th scope="col">File</th>
                    <th scope="col">User</th>
                    <th scope="col">Time</th>
                    <th scope="col">Change</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="arquivo" items="${arquivos}">
                    <tr>
                        <td>${arquivo.id}</td>
                        <td>${arquivo.category}</td>
                        <td>${arquivo.data}</td>
                        <td>${arquivo.userId}</td>
                        <td>${arquivo.timeStamp}</td>
                        <td>
                            <form action="change" method="Post">
                                <input type='hidden' name='id' value='${arquivo.id}'>
                                <input type='hidden' name='data' value='${arquivo.data}'>
                                <input type='hidden' name='category' value='${arquivo.category}'>
                                <input type='hidden' name='userId' value='${arquivo.userId}'>
                                <button type="submit" class="btn btn-outline-dark">Change</button>
                            </form>
                        </td>
                        <td>
                            <form action="remove" method="get">
                                <input type='hidden' name='id' value='${arquivo.id}'>
                                <button type="submit" class="btn btn-outline-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <h2 class="d-flex justify-content-center mt-5">Add file</h2>
    <form name="info" method="Post" action="Lista">
        <div class="form-row mx-5 my-3">
            <div class="col">
                <label>User</label>
                <input type="text" class="form-control" name="userId" placeholder="User">
            </div>
            <div class="col">
                <label>Category</label>
                <input type="text" class="form-control" name="category" placeholder="category">
            </div>
        </div>
        <div class="form-row mx-5">
            <div class="col">
                <label>File</label>
                <textarea class="form-control" name="data" placeholder="${data}" rows="3">
        </textarea>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-lg btn-block btn-outline-success mx-5 my-3" value="Save">Add</button>
        </div>
    </form>

</html>