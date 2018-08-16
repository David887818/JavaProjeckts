package servlet;

import manager.CategoryManager;
import manager.PostManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/homeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/");
        } else {
            CategoryManager categoryManager = new CategoryManager();
            PostManager postManager = new PostManager();
            req.setAttribute("categories", categoryManager.getCategories());
            req.setAttribute("posts", postManager.getPosts());
            if (user.getUserType() == UserType.ADMIN) {
                req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/homeUser.jsp").forward(req, resp);
            }
        }

    }
}
