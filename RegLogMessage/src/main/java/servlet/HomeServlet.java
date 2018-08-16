package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.User;

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
            UserManager userManager = new UserManager();
            MessageManager messageManager = new MessageManager();
            req.setAttribute("user", userManager.getUsers());
            req.setAttribute("currentUser", user);
            req.setAttribute("message", messageManager.getMessageById(user.getId()));
            req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
        }

    }
}
