package servlet;

import manager.PostManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostManager postManager = new PostManager();
        String id = req.getParameter("id");
        try {
            postManager.deletePostById(Long.parseLong(id));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/homeServlet");

    }
}
