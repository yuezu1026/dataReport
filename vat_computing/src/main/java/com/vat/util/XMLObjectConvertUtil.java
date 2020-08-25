package com.vat.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class XMLObjectConvertUtil {
	/**
	 * 将xml文件转换为Map集合
	 * @param String类型的xml
	 * @return map集合
	 */
	public static Map<String, String> getMap(String xml) throws Exception{
		try{
		Map<String, String> maps = new HashMap<String, String>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes("utf-8"));
		Document document = documentBuilder.parse(inputStream);
		document.getDocumentElement().normalize();
		//获得所有的节点
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			//如果为标记节点 如 <username>
			if(node.getNodeType() == node.ELEMENT_NODE){
				Element e = (Element) node;
				//将节点名称与节点内容放进map集合中
				maps.put(e.getNodeName(),e.getTextContent());
			}
		}
		return maps;
	}catch(Exception e){
		e.printStackTrace();
	}
		return null;		
	}
	
	/**
	 * 将Map集合转换成String类型的xml
	 * @param maps
	 * @return
	 * @throws Exception
	 */
	public String getXml(Map<String, String> maps) throws Exception{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		//创建根目录
		Element  root = document.createElement("xml");
		//添加根目录进文档
		document.appendChild(root);
		//遍历所有的key
		for(String key:maps.keySet()){
			//获得key所对应的value
			String value= maps.get(key);
			if(value == null){
				value ="";
			}
			//去掉空格
			value = value.trim();
			//创建子节点
			Element filed = document.createElement(key);
			//将key所对应的value放入node中
			filed.appendChild(document.createTextNode(value));
			//将子节点放入根节点中
			root.appendChild(filed);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer =transformerFactory.newTransformer();
		DOMSource domSource = new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(domSource, sr);
		String output = sw.getBuffer().toString();
		return output;
		
	}
	/**
	 * 将java对象转换成String类型的xml
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String getXml(Object obj) throws Exception{
		StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller mar = context.createMarshaller();
		//统一编码
		mar.setProperty(mar.JAXB_ENCODING, "utf-8");
		mar.setProperty(mar.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		mar.marshal(obj, sw);
		return sw.toString();	
	}
}