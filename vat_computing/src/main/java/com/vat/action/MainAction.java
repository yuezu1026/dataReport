package com.vat.action;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.vat.util.MD5Utils;

@Controller
public class MainAction {
    
    private static final Logger logger = LoggerFactory.getLogger(MainAction.class);

    /**
     * 创建二维码
     */
    @RequestMapping(value = "/user/getQrCode.do", method = { RequestMethod.POST, RequestMethod.GET })
    public void createCode(HttpServletResponse response) {
	// 创建订单
	String orderId = "1";
	String orderInfo = createOrderInfo(orderId);
	if (orderInfo != null) {
	    // 得到统一下单 API
	    String codeUrl = httpOrder(orderInfo);
	    codeUrl = orderInfo;
	    if (codeUrl != null) {
		// 利用 返回的预支付交易链接(codeUrl) 生成扫描的二维码
		try {
		    int width = 200; // 宽200
		    int height = 200; // 高200
		   // String format = "jpg"; // 图片格式
		    // 开始创建二维码
		   // Hashtable hs = new Hashtable();
		    // 设置编码格式
		  //  hs.put(EncodeHintType.CHARACTER_SET, "utf-8");
		    // 设置二维码
		   // BitMatrix bit = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, width, height);
		    OutputStream out = null;
		    //out = response.getOutputStream();
		    // out = ServletActionContext.getResponse().getOutputStream();
		    //MatrixToImageWriter.writeToStream(bit, format, out);
		    
		    out=response.getOutputStream();
                    QRCodeWriter writer=new QRCodeWriter();
                    BitMatrix m=writer.encode(codeUrl, BarcodeFormat.QR_CODE, height,width);
                    MatrixToImageWriter.writeToStream(m, "png", out);
		    
		    out.flush();
		    out.close();
		} catch (Exception e) {
		    logger.error("error",e);
		}
	    }
	}
    }

    /**
     * 调统一下单API
     */
    private String httpOrder(String orderInfo) {
	// 微信支付统一接口
	String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	try {
	    // 连接微信统一支付url
	    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	    // 加入数据
	    conn.setRequestMethod("POST");
	    // 打开传输输出流
	    conn.setDoOutput(true);
	    // 获取输出流
	    BufferedOutputStream buffer = new BufferedOutputStream(conn.getOutputStream());
	    buffer.write(orderInfo.getBytes());
	    buffer.flush();
	    buffer.close();
	    // 获取输入流
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    // 接受数据
	    String line = null;
	    StringBuffer sb = new StringBuffer();
	    // 将输入流中的信息放在sb中
	    while ((line = reader.readLine()) != null) {
		sb.append(line);
	    }
	    // 解析xml成对象
	    JAXBContext context = JAXBContext.newInstance(UnifiedOrderRespose.class);
	    // 反序列化成对象
	    Unmarshaller unmarshaller = context.createUnmarshaller();
	    // 读取数据
	    StringReader sr = new StringReader(sb.toString());
	    // 根据微信Demo 将xml转换成Map
	    Map<String, String> ResponseMap = new HashMap<String, String>();
	    // 创建文档类型
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    // 将获取到的xml放入输入流中
	    InputStream in = new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
	    Document doc;
	    doc = db.parse(in);
	    NodeList nl = doc.getDocumentElement().getChildNodes();
	    for (int i = 0; i < nl.getLength(); i++) {
		Node node = nl.item(i);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
		    Element e = (Element) node;
		    ResponseMap.put(e.getNodeName(), e.getTextContent());
		}
	    }
	    /*
	     * 转换成UnifiedOrderRespose对象 UnifiedOrderRespose unRespose =
	     * (UnifiedOrderRespose) unmarshaller.unmarshal(new
	     * File("C:/Users/Administrator/Workspaces/MyEclipse 10/weixinpay/WebRoot/xmls/Result.xml"
	     * )); UnifiedOrderRespose unRespose = (UnifiedOrderRespose)
	     * unmarshaller.unmarshal(sr);
	     * System.out.println(unRespose.getReturn_code()+"|"+unRespose.getReturn_msg());
	     * unRespose.setReturn_code("SUCCESS"); unRespose.setReturn_msg("SUCCESS");
	     * unRespose.setCode_url("www.baidu.com");
	     */
	    // 如果请求成功返回 ，则返回支付链接
	    if (null != ResponseMap && "SUCCESS".equals(ResponseMap.get("return_code"))
		    && "SUCCESS".equals(ResponseMap.get("return_msg"))) {
		return ResponseMap.get("code_url");
	    } else {
		return null;
	    }
	} catch (SAXException e) {
	    logger.error("error",e);
	} catch (ParserConfigurationException e) {
	    logger.error("error",e);
	} catch (JAXBException e) {
	    logger.error("error",e);
	} catch (MalformedURLException e) {
	    logger.error("error",e);
	} catch (IOException e) {
	    logger.error("error",e);
	}

	return null;
    }

    /**
     * 生成订单,返回xml文件
     */
    private String createOrderInfo(String orderId) {
	UnifiedOrderRequest un = new UnifiedOrderRequest();
	un.setAppid("wxa981bab323301bee");// 公众账号ID
	un.setMch_id("gh_4118dc0010f8");// 商户号
	un.setNonce_str(UUID.randomUUID().toString().substring(0, 30));// 随机字符串 <span
								       // style="color:#ff0000;"><strong>说明2(见文末)</strong></span>
	un.setBody("增值税");// 商品描述
	un.setOut_trade_no(orderId);// 商户订单号
	un.setTotal_fee("10000"); // 金额需要扩大100倍:1代表支付时是0.01
	un.setSpbill_create_ip("121.35.3.166");// 终端IP
	un.setNotify_url("https://www.wangyz.cn/v/login.jsp");// 支付成功后，回调的地址
	un.setSign(createSign(un));// 签名
	un.setTrade_type("NATIVE");// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
	try {
	    // 将订单对象转为xml格式
	    StringWriter sw = new StringWriter();
	    JAXBContext context = JAXBContext.newInstance(un.getClass());
	    Marshaller mar = context.createMarshaller();
	    mar.setProperty(mar.JAXB_ENCODING, "utf-8");
	    mar.setProperty(mar.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    mar.marshal(un, sw);
	    /*
	     * 将xml转换为对象 JAXBContext context =
	     * JAXBContext.newInstance(UnifiedOrderRequest.class); Unmarshaller unmarshaller
	     * = context.createUnmarshaller(); UnifiedOrderRequest unifiedOrderRequest =
	     * (UnifiedOrderRequest)unmarshaller.unmarshal(new StringReader("xml文件"));
	     */
	    return sw.toString();
	} catch (JAXBException e) {
	    logger.error("error",e);
	}
	return null;
    }

    /**
     * 生成数字签名
     */
    private String createSign(UnifiedOrderRequest un) {
	// 创建可排序的Map集合
	SortedMap<String, String> packageParms = new TreeMap<String, String>();
	packageParms.put("appid", un.getAppid());
	packageParms.put("body", un.getBody());
	packageParms.put("mch_id", un.getMch_id());
	packageParms.put("nonce_str", un.getNonce_str());
	packageParms.put("notify_url", un.getNotify_url());
	packageParms.put("out_trade_no", un.getOut_trade_no());
	packageParms.put("spbill_create_ip", un.getSpbill_create_ip());
	packageParms.put("trade_type", un.getTrade_type());
	packageParms.put("total_fee", un.getTotal_fee());

	StringBuffer sb = new StringBuffer();
	// 按照字典排序
	Set set = packageParms.entrySet();
	// 迭代器
	Iterator iterator = set.iterator();
	while (iterator.hasNext()) {
	    Map.Entry entry = (Entry) iterator.next();
	    String key = (String) entry.getKey();
	    String value = (String) entry.getValue();
	    // 为空不参与签名,参数名区分大小写
	    if (null != value && !"".equals(value) && !"key".equals(key) && !"sign".equals(key)) {
		sb.append(key + "=" + value + "&");
	    }
	}
	/* 拼接 key,设置路径:微信商户平台(pay.weixin.com)->账户设置->API安全-->秘钥设置 */
	sb.append("key=" + "28dc5473a9c148449337fae23b091586");
	MD5Utils md5 = new MD5Utils();

	String sign = md5.getMD5(sb.toString());
	// String sign = md5.getMD5(sb.toString(),"utf-8");
	return sign;
    }

}