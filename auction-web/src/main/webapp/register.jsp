<%--
  Created by IntelliJ IDEA.
  User: dulanjaya
  Date: 5/30/2025
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register - Online Auction System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row d-flex justify-content-center align-items-center p-4">
        <div class="col-12 col-sm-12 col-md-6 col-lg-6 card">
            <div class="card-body">
                <h4 class="text-center">Register</h4>
                <form action="UserRegister" method="post">
                    <div class="mt-2">
                        <span class="form-label">Username</span>
                        <input type="text" class="form-control" name="username"/>
                    </div>
                    <div class="col-12 mt-3">
                        <div class="row">
                            <div class="col-12 col-md-6 col-lg-6">
                                <span class="form-label">First Name</span>
                                <input type="text" class="form-control" name="firstName" />
                            </div>
                            <div class="col-12 col-md-6 col-lg-6">
                                <span class="form-label">Last Name</span>
                                <input type="text" class="form-control" name="lastName"/>
                            </div>
                        </div>
                    </div>
                    <div class="mt-2">
                        <span class="form-label">Email address</span>
                        <input type="email" class="form-control" name="email"/>
                    </div>
                    <div class="mt-2">
                        <span class="form-label">Password</span>
                        <input type="password" class="form-control" name="password" aria-describedby="passwordHelpBlock"/>
                        <div class="form-text">
                            Your password must be 8 characters long, contain letters(A-a) and numbers, special characters and must not contain spaces, or emoji.
                        </div>
                    </div>
                    <div class="mt-3 d-grid">
                        <button type="submit" class="btn btn-primary">Continue</button>
                    </div>
                    <div class="mt-4 d-flex justify-content-center">
                        <a href="login.jsp" class="link-secondary link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover">
                            Already have an Account? LogIn
                        </a>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-12 text-center mt-3">
            <span class="text-muted">Developed By Dulanjaya Bhanu</span>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>
