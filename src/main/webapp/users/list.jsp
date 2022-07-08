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
                    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/user/add">
                        <span data-feather="user-plus"></span>
                        Add a new user
                    </a>
                </div>
            </div>

            <div class="card border">
                <h5 class="card-header text-primary">Users list</h5>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Username</th>
                                <th scope="col">E-mail</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="userList" scope="request" type="java.util.List"/>
                            <c:forEach items="${userList}" var="user">
                                <jsp:useBean id="user" scope="request" class="pl.coderslab.entity.User"/>
                                <tr>
                                    <td class="align-middle">${user.id}</td>
                                    <td class="align-middle">${user.userName}</td>
                                    <td class="align-middle">${user.email}</td>
                                    <td class="align-middle">
                                        <div class="btn-group me-2" role="group" aria-label="User actions">
                                            <a type="button" class="btn btn-sm btn-outline-secondary"
                                               href="${pageContext.request.contextPath}/user/delete?userId=${user.id}">Remove</a>
                                            <a type="button" class="btn btn-sm btn-outline-secondary"
                                               href="${pageContext.request.contextPath}/user/edit?userId=${user.id}">Edit</a>
                                            <a type="button" class="btn btn-sm btn-outline-secondary"
                                               href="${pageContext.request.contextPath}/user/show?userId=${user.id}">Show</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
