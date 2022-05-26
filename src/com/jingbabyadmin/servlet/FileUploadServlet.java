package com.jingbabyadmin.servlet;

import com.jingbabyadmin.constant.SystenConstant;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet("/fileUpload")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part path = req.getPart("file");
        String substring = path.getSubmittedFileName().substring(path.getSubmittedFileName().lastIndexOf("."));
        String filename = UUIDUtils.getId() + substring;
        System.out.println("name = " + filename);
        InputStream inputStream = path.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(SystenConstant.SYSTEM_BRAND_PATH + filename);

        byte[] size = new byte[SystenConstant.FILEOUTPUT_SIZE];

        while(true){
           int len = inputStream.read(size);
           if(len == -1){
               break;
           }
           fileOutputStream.write(size,0,len);
        }

        fileOutputStream.close();
        inputStream.close();
        resp.getWriter().write(filename);
    }
}
