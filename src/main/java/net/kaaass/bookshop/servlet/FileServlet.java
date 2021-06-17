package net.kaaass.bookshop.servlet;

import net.kaaass.bookshop.util.Constants;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

@WebServlet("/upload/*")
public class FileServlet extends HttpServlet {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FileServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File file = new File(Constants.UPLOAD_FOLDER, filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        try {
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (NoSuchFileException e) {
            log.info("文件 {} 不存在", file, e);
            throw new ServletException("文件不存在");
        }
    }
}