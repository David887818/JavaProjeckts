package servlet;

import manager.CategoryManager;
import manager.PostManager;
import model.Post;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addPostServlet")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/");
        } else {
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String catId = req.getParameter("category");
            String picUrl=req.getParameter("picUrl");
            PostManager postManager = new PostManager();
            CategoryManager categoryManager = new CategoryManager();
            Post post = Post.builder()
                    .title(title)
                    .content(content)
                    .createdDate(new Date())
                    .category(categoryManager.getCategoryById(Long.parseLong(catId)))
                    .picUrl(picUrl)
                    .user(user)
                    .build();
            postManager.add(post);
            resp.sendRedirect("/homeServlet");
        }
    }
}
