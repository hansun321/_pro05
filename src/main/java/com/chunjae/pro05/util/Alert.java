package com.chunjae.pro05.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Alert {
    public static void alert(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println(String.format("alert('%s');", msg));
        out.println("</script>");
        out.flush();
    }
}
