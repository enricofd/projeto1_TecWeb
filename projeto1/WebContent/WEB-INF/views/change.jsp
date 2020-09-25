<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*,br.edu.insper.mvc.*" %>
<!DOCTYPE html>
<html>

<head>
    <title>Make Change</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
</head>

<body>
    <h2 class="d-flex justify-content-center mt-5">Make Change</h2>
    <form name="info" method="Get" action="change">
        <div class="mx-5">
            <div class="form-row my-3">
                <div class="col">
                    <label>Id</label>
                    <input type="text" class="form-control" name="id" value="${id}" placeholder="${id}" readonly />
                </div>
                <div class="col">
                    <label>Category</label>
                    <input type="text" class="form-control" name="category" placeholder="${category}" />
                </div>
            </div>
        </div>
        <div class="col">
            <input type="hidden" name="userId" value="${userId}" />
        </div>
        <div class="form-row mx-5">
            <div class="col">
                <label>File</label>
                <textarea class="form-control" name="data" placeholder="${data}" rows="3"></textarea>
            </div>
        </div>
        <div class="d-flex justify-content-center">
            <button type="submit" class="btn btn-lg btn-block btn-outline-success mx-5 my-3" value="Save">
                Change
            </button>
        </div>
    </form>
</body>

</html>