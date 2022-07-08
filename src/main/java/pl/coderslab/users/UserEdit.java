package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    private static final String VALID_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _userId = request.getParameter("userId");
        if (validUserId(_userId)) {
            User userToEdit = userDao.read(Integer.parseInt(_userId));
            request.setAttribute("user", userToEdit);
            getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean validUserId(String userId) {
        return userId != null && !userId.isBlank() && userId.matches("\\d+")
                && userDao.read(Integer.parseInt(userId)) != null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var _username = request.getParameter("username");
        var _email = request.getParameter("email");
        var _password = request.getParameter("password");
        var _userId = request.getParameter("userId");
        var userDao = new UserDao();
        if (validUsername(_username) && validEmail(_email) && validPassword(_password) && validUserId(_userId)) {
            int userId = Integer.parseInt(_userId);
            var userToUpdate = new User(userId, _username, _email, _password);
            var _user = userDao.update(userToUpdate);
            String redirectUrlSuffix;
            if (_user != null) {
                redirectUrlSuffix = "/user/list";
            } else {
                redirectUrlSuffix = "/user/addError";
            }
            response.sendRedirect(request.getContextPath() + redirectUrlSuffix);
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
