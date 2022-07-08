package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserShow", value = "/user/show")
public class UserShow extends HttpServlet {
    private final UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _userId = request.getParameter("userId");
        if (validUserId(_userId)) {
            User userToShow = userDao.read(Integer.parseInt(_userId));
            request.setAttribute("user", userToShow);
            getServletContext().getRequestDispatcher("/users/show.jsp").forward(request, response);
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
    }
}
