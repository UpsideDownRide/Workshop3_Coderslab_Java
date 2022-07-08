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
                <h5 class="card-header text-primary">User Details</h5>
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <jsp:useBean id="user" scope="request" class="pl.coderslab.entity.User"/>
                        <li class="list-group-item">User ID: ${user.id}</li>
                        <li class="list-group-item">Username: ${user.userName}</li>
                        <li class="list-group-item">User Email: ${user.email}</li>
                    </ul>
                </div>
            </div>
        </main>
    </div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
