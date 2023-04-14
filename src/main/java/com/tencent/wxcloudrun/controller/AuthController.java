package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.utils.CheckSignatureUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@RestController
public class AuthController {
    @GetMapping("/wx")
    public void checkSign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
