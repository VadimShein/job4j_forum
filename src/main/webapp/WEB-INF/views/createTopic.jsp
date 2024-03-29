<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Create</title>
</head>

<body>
<div class="container">
    <div class="row" id="login">
        <a class="nav-link" href="<c:url value="/login?logout=true"/>"> <c:out value="${userName}"/> | Выйти</a>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Создание темы</h3>
            </div>
            <div class="card-body">
                <form action="<c:url value='/saveTopic'/>" method="post">
                    <div class="form-group">
                        <div id="name">
                            <label>Тема:</label>
                            <input type="text" class="form-control" name="name" title="Заполните поле: Тема">
                        </div>
                        <div id="description">
                            <label>Описание:</label>
                            <textarea maxlength="255" rows="3" class="form-control" name="description" title="Заполните поле: Описание" style="height: 113px"></textarea>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>