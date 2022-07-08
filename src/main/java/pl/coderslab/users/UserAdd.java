package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserAdd", value = "/user/add")
public class UserAdd extends HttpServlet {
    private static final String VALID_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var _username = request.getParameter("username");
        var _email = request.getParameter("email");
        var _password = request.getParameter("password");
        var userDao = new UserDao();
        if (validUsername(_username) && validEmail(_email) && validPassword(_password)) {
            var userToAdd = new User(_username, _email, _password);
            var _user = userDao.create(userToAdd);
            String redirectUrlSuffix = "/user/list";
            if (_user != null) {
                response.sendRedirect(request.getContextPath() + redirectUrlSuffix);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean validPassword(String password) {
        return notBlankNotNull(password) && (password.length() > 8 && password.length() < 40);
    }

    private boolean validEmail(String email) {
        return notBlankNotNull(email) && email.matches(VALID_EMAIL_REGEX) && email.length() < 100;
    }

    private boolean validUsername(String username) {
        return notBlankNotNull(username) && username.length() < 30 && username.length() >= 3;
    }

    private boolean notBlankNotNull(String param) {
        return param != null && !param.isBlank();
    }
}
