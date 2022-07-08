<!doctype html>
<html lang="en">
<head>
    <%@ include file="/head.jsp" %>
    <title>Workshop 3 - User CRUD</title>
</head>
<body>
<%@ include file="/navbar-top.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/navbar-side.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Users CRUD</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/user/list">
                        <span data-feather="user"></span>
                        User list
                    </a>
                </div>
            </div>
            <div class="card border">
                <jsp:useBean id="user" scope="request" class="pl.coderslab.entity.User"/>
                <form method="POST">
                    <h5 class="card-header text-primary">Add User</h5>
                    <div class="card-body">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username"
                                   value="${user.userName}">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </div>
                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary">Edit user</button>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
