package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/getFileServlet")
public class GetFileServlet extends HttpServlet {

    private static final String IMAGE_ROOT_DIR = "A:\\img";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picName = req.getParameter("picName");
        String picUrl = IMAGE_ROOT_DIR + File.separator + picName;

        try (InputStream in = new FileInputStream(picUrl);
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[4096];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }

    }
}
