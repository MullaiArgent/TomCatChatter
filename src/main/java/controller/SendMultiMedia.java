package controller;

import datamanagement.JDBC;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/send_multimedia")
public class SendMultiMedia extends HttpServlet {
    JDBC db = new JDBC();
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId = req.getRemoteUser();
        String friendId = (String) req.getSession().getAttribute("friendId");

        String send_file;
        String root = "C:\\Users\\mulla\\OneDrive\\Documents\\GitHub\\TomCatChatter\\src\\main\\webapp\\images\\";
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        String file = "";
        try {
            List<FileItem> multipleFiles = sf.parseRequest(req);
            for (FileItem i : multipleFiles){
                file = i.getName();
                i.write(new File(root + file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (".mp4".equals(file.substring(file.lastIndexOf('.')))){
            send_file = "videos//" + file;
            if (!send_file.equals("videos//")) {
                try {
                    db.dml("INSERT INTO \"MESSAGES\" VALUES ('"+ userId +"','"+ friendId +"',now(),'" + send_file + "','video');");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            send_file= root + file;
                try {
                    db.dml("INSERT INTO \"MESSAGES\" VALUES ('"+ userId +"','"+ friendId +"',now(),'" + send_file + "','image');");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
        }
        res.sendRedirect("app");
    }
}
