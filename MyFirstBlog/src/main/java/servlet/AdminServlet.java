package servlet;

import manager.CategoryManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/adminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserType() != UserType.ADMIN) {
            resp.sendRedirect("/");
        } else {
            CategoryManager categoryManager = new CategoryManager();
            req.setAttribute("categories", categoryManager.getCategories());
            req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
        }

    }
}
