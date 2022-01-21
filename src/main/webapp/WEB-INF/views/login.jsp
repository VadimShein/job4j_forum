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

    <title>Login</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <c:if test="${not empty errorMessage}">
            <div style="color:#ff0000; font-weight: bold; margin: 30px 0;">
                    ${errorMessage}
            </div>
        </c:if>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Авторизация</h3>
            </div>
            <div class="card-body">
                <form name="login" action="<c:url value='/login'/>" method="POST">
                    <div class="form-group">
                        <label>Имя пользователя</label>
                        <input type="text" class="form-control" name="username" title="Заполните поле: имя пользователя"
                               value="<%=(request.getParameter("email")!=null)?request.getParameter("email"):""%>">
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="password" class="form-control" name="password" title="Заполните поле: пароль">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Войти</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <form action="<c:url value='/reg'/>">
                    <button type="submit" class="btn btn-primary">Регистрация</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>