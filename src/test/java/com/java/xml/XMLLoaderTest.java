/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: XMLLoaderTest.java
 * @Package com.java.xml
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年8月30日 上午9:06:33
 * @version
 */
package com.java.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.util.Set;

/**
 * @author liqiuwei
 * @create time:2016年8月30日上午9:06:33
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class XMLLoaderTest {

    public static void main(String[] args) {

        String path = "conf/persons.xml";
        //Thread.currentThread().getClass().
        String path1 = XMLLoaderTest.class.getResource("/" + path).getPath();
        File file = new File(path1);
        System.out.println(path + "##filepath==" + file.length());

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setIgnoringComments(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(false);
        documentBuilderFactory.setCoalescing(false);
        documentBuilderFactory.setExpandEntityReferences(true);
        DocumentBuilder documentBuilder;
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            NodeList xml = document.getChildNodes();
            System.out.println(xml.getLength());
/*			
            for(int i=0;i<nodeList.getLength();i++){
				Node node=nodeList.item(i);
				NodeList  nodes =node.getChildNodes();
				for(int j=0;j<nodes.getLength();j++){
					Node node2=nodes.item(i);
				System.out.println(node2.getNodeName()+"##"+node2.getNodeType()+"name=="+node.getNodeName()+"##"+node.getNodeType());
				}
			}*/

            for (int i = 0; i < xml.getLength(); i++) {
                Node roots = xml.item(i);
                NodeList persons = roots.getChildNodes();
                System.out.println(roots.getNodeName() + "::persons==" + persons.getLength());
                for (int j = 0; j < persons.getLength(); j++) {
                    Node person = persons.item(j);
                    System.out.println("person==" + person.getNodeName());
                    NodeList pros = person.getChildNodes();
                    for (int k = 0; k < pros.getLength(); k++) {
                        Node item = pros.item(k);
                        System.out.println(item.getNodeName() + ":" + item.getTextContent());
                    }
                }
            }
            System.out.println("XML解析完毕");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //只验证
        Element ele;
        //ele.getAttribute(name)
        Set set = new java.util.HashSet();
        try {
            set.add("sd");


            if (1 == 1) {
                throw new Exception(set.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
