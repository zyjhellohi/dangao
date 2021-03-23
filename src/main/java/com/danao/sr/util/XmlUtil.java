package com.danao.sr.util;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    public static Map<String, String> xmlToMap(InputStream ins) {
        HashMap<String, String> map = null;
        try {
            map = new HashMap<String, String>();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(ins);
            Element root = doc.getRootElement();
            List<Element> list = (List<Element>) root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            ins.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getXml(Object b) {
        Document document = DocumentHelper.createDocument();
        try {
            // 创建根节点元素
            Element root = document.addElement("xml");
            Field[] field = b.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有有属性
                String name = field[j].getName(); // 获取属属性的名字
                if (!name.equals("serialVersionUID")) {//去除串行化序列属性
                    name = name.substring(0, 1).toUpperCase()
                            + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    Method m = b.getClass().getMethod("get" + name);
                    // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                    String propertievalue = (String) m.invoke(b);// 获取属性值
                    Element propertie = root.addElement(name);
                    propertie.setText(propertievalue);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return document.asXML();
    }
}
