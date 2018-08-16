package servlet;

import manager.AdvertisementManager;
import manager.CategoryManager;
import model.Advertisement;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addAdsServlet")
public class AddAdsServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> fileItems = upload.parseRequest(req);
                User user = (User) req.getSession().getAttribute("user");
                String title = "";
                String description = "";
                String price = "";
                String catId = "";
                String picUrl = "";
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        if (fileItem.getFieldName().equals("title")) {
                            title = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("description")) {
                            description = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("price")) {
                            price = fileItem.getString();
                        } else if (fileItem.getFieldName().equals("catId")) {
                            catId = fileItem.getString();
                        }
                    } else {
                        File rootPath = new File("A:\\img");
                        if (!rootPath.exists()) {
                            rootPath.mkdirs();
                        }
                        picUrl = System.currentTimeMillis() + "_" + fileItem.getName();
                        fileItem.write(new File(rootPath, picUrl));
                    }
                }

                AdvertisementManager advertisementManager = new AdvertisementManager();
                CategoryManager categoryManager = new CategoryManager();
                Advertisement advertisement = Advertisement.builder()
                        .title(title)
                        .description(description)
                        .price(Integer.parseInt(price))
                        .category(categoryManager.getCategoryById(Integer.parseInt(catId)))
                        .picUrl(picUrl)
                        .author(user)
                        .build();
                advertisementManager.add(advertisement);
                resp.sendRedirect("/loginServlet");

            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
}

