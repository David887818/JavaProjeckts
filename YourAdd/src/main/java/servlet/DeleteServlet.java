package servlet;

import manager.AdvertisementManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s=req.getParameter("id");

        AdvertisementManager advertisementManager=new AdvertisementManager();
        advertisementManager.deleteById(Integer.parseInt(s));
        resp.sendRedirect("/homeAdmin");
    }
}
