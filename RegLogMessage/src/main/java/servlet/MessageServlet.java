package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(urlPatterns = "/messageServlet")
public class MessageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/");
        } else {
            String id = req.getParameter("id");
            String text = req.getParameter("sms");
            MessageManager messageManager = new MessageManager();
            UserManager userManager = new UserManager();
            User user1 = userManager.getUserById(Integer.parseInt(id));
            Message message = Message.builder()
                    .text(text)
                    .to(user1)
                    .from(user)
                    .build();
            messageManager.add(message);
            resp.sendRedirect("/homeServlet");

        }
    }
}
