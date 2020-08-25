package com.vat.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Controller
public class WeiXinController {

    private final static Logger logger = LoggerFactory.getLogger(WeiXinController.class);

    // @GetMapping(value = "/user/getQrCode2.do")
   // @RequestMapping(value = "/user/getQrCode.do", method = { RequestMethod.POST, RequestMethod.GET })
    public void getQrCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
	MainAction mainAction = new MainAction();
	//mainAction.setOrderId("1");
	//mainAction.createCode(response);
	
	return;
    }

    @RequestMapping(value = "/user/getWebAddrQrCode.do", method = { RequestMethod.POST, RequestMethod.GET })
    public void getErWeiCode(HttpServletRequest request, HttpServletResponse response) {
	String url = "https://www.vat-computing.com/";
	if (url != null && !"".equals(url)) {
	    ServletOutputStream stream = null;
	    try {
		int width = 200;
		int height = 200;
		stream = response.getOutputStream();
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
		MatrixToImageWriter.writeToStream(m, "png", stream);
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		if (stream != null) {
		    try {
			stream.flush();
			stream.close();
		    } catch (IOException e) {
			logger.error("error", e);
		    }

		}
	    }
	}
    }

}
