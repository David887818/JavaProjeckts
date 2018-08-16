package servlet;

import manager.AdvertisementManager;
import manager.CategoryManager;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/homeUser")
public class UserHomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getUserType() == UserType.ADMIN) {
            resp.sendRedirect("/");
        } else {
            UserManager userManager=new UserManager();
            AdvertisementManager advertisementManager=new AdvertisementManager();
            CategoryManager categoryManager=new CategoryManager();
            req.setAttribute("user", userManager.getUsers());
            req.setAttribute("currentUser", user);
            req.setAttribute("advertisement", advertisementManager.getAdvertisements());
            req.setAttribute("category", categoryManager.getCategoryes());
            req.getRequestDispatcher("/WEB-INF/homeUser.jsp").forward(req, resp);
        }
    }
}
