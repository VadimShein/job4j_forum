<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- jQuery, Popper.js -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script>
        function validate() {
            let rsl = true
            let atr = $('.form-control')
            for (let node of atr) {
                if (node.value === '' || node.value === null) {
                    alert(node.getAttribute('title'));
                    rsl = false
                    break
                }
            }
            return rsl
        }
    </script>

    <title>Discussion</title>
</head>

<body>
<div class="container">
    <div class="row" id="login">
        <a class="nav-link" href="<c:url value="/login?logout=true"/>"> <c:out value="${userName}"/> | Выйти</a>
        <a class="nav-link" href="<c:url value="/index"/>"> На главную</a>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>№<c:out value="${topic.id}"/> <c:out value="${topic.name}"/></h3>
            </div>

            <div class="card-body">
                <div class="table">
                    <c:out value="${topic.description}"/>
                </div>

                <table class="table table-bordered" style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th style="width: 40px; text-align: center">№</th>
                        <th style="text-align: center">Комментарий</th>
                        <th style="width: 20%; text-align: center">Автор</th>
                        <th style="text-align: center">Дата</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${messages}" var="message">
                        <tr>
                            <td><c:out value="${message.id}"/></td>
                            <td><c:out value="${message.text}"/></td>
                            <td><c:out value="${message.author}"/></td>
                            <td>
                                <fmt:formatDate type="time" value="${message.created.time}" pattern="dd.MM.yyyy HH:mm:ss"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <form action="<c:url value="/saveMessage"/>" method="post">
                    <div class="form-group">
                        <div id="id">
                            <input type="hidden" name="id" value="${topic.id}">
                        </div>
                        <div id="author">
                            <input type="hidden" name="author" value="${userName}">
                        </div>
                        <div id="text">
                            <label>Комментарий:</label>
                            <textarea maxlength="255" rows="2" class="form-control" name="text"
                                      title="Заполните поле: Сообщение" style="height: 75px"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Добавить комментарий</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>