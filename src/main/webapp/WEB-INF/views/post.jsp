<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>№:<c:out value="${post.id}"/> <c:out value="${post.name}"/></h3>
            </div>
            <div class="card-body">
                <div>
                    <label>Описание:</label>
                    <c:out value="${post.description}"/>
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
                    <c:forEach items="${post.messages}" var="message">
                        <tr>
                            <td><c:out value="${message.id}"/></td>
                            <td><c:out value="${message.text}"/></td>
                            <td><c:out value="${message.author}"/></td>
                            <td><c:out value="${message.created}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <form action="<c:url value="/save"/>" method="post">
                    <div class="form-group">
                        <div id="id">
                            <input type="hidden" name="id" value="${post.id}">
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