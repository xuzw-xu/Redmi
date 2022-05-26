package com.jingbabyadmin.servlet;

import com.jingbabyadmin.constant.SystenConstant;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/common/getImage")
public class ShowImgServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filename = req.getParameter("name");

        FileInputStream fileInputStream = new FileInputStream(SystenConstant.SYSTEM_BRAND_PATH + filename);
        ServletOutputStream outputStream = resp.getOutputStream();

        resp.setContentType("image/JPEG");

        byte[] size = new byte[SystenConstant.FILEOUTPUT_SIZE];
        while(true){
            int len = fileInputStream.read(size);
            if(len == -1){
                break;
            }
            outputStream.write(size,0,len);
        }

        outputStream.close();
        fileInputStream.close();
    }
}
