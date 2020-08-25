package com.vat.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/code")
public class SecCodeController {
    
    private final static Logger logger = LoggerFactory.getLogger(SecCodeController.class);
    
    private static final String SESSION_SECURITY_CODE ="SESSION_SECURITY_CODE";

    @RequestMapping
    public void generate(HttpServletResponse response){
	logger.info("generate start...");
        
        String code = drawImg();
        
        //Subject currentUser = SecurityUtils.getSubject();  
        //Session session = currentUser.getSession();
        //session.setAttribute(SESSION_SECURITY_CODE, code);
        
        ByteArrayOutputStream output =  null;
        ServletOutputStream out = null;
        try {
            output = new ByteArrayOutputStream();  
            out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            logger.error("error",e);
        }finally {
            if(output != null) {
        	try {
        	    output.close();
        	}catch(Exception e) {
        	    logger.error("error",e);
        	}
            }
        }
    }
    
    private String drawImg(){
	ByteArrayOutputStream output = null;
        String code = "";
        for(int i=0; i<4; i++){
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman",Font.PLAIN,20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66,2,82);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int)x, (int)baseY);
        g.dispose();
        try {
            output = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            logger.error("error",e);
        }finally {
            if(output != null) {
        	try {
        	    output.close();
        	}catch(Exception e) {
        	    logger.error("error",e);
        	}
            }
        }
        return code;
    }
    
    private char randomChar(){
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }
}