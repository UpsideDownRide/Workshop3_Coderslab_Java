package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDelete", value = "/user/delete")
public class UserDelete extends HttpServlet {
    private final UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _userId = request.getParameter("userId");
        if (validUserId(_userId)) {
            User userToDelete = userDao.read(Integer.parseInt(_userId));
            request.setAttribute("user", userToDelete);
            getServletContext().getRequestDispatcher("/users/delete.jsp").forward(request, response);
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
        String _userId = request.getParameter("userId");
        if (validUserId(_userId)) {
            userDao.delete(Integer.parseInt(_userId));
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
