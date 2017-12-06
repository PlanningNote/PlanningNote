

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class DomTest04 
{
    public static void main(String[] args) 
    {
        try
        {
            //DOM Document 객체 생성하기 위한 메서드 
           DocumentBuilderFactory f 
            = DocumentBuilderFactory.newInstance();
           //DOM 파서로부터 입력받은 파일을 파싱하도록 요청
           DocumentBuilder parser = f.newDocumentBuilder();
           
           //XML 파일 지정
           String url = "http://www.google.co.kr/ig/api?weather=seoul";
           
           //웹상에서 읽어오는 데이터는 StreamReader를 이용해 읽어야 한다.
           URL u = new URL(url);
           InputStreamReader is =new InputStreamReader(u.openStream(), "euc-kr");
           
           Document xmlDoc = null; 
           //DOM 파서로부터 입력받은 파일을 파싱하도록 요청 
           xmlDoc = parser.parse(new InputSource(is));
           
           //루트 엘리먼트 접근 
           Element root = xmlDoc.getDocumentElement();
           
           NodeList ccNodes = root.getElementsByTagName("current_conditions");
           Node ccNode = ccNodes.item(0);
           Element ccElement = (Element)ccNode;
           NodeList cNodes = ccElement.getElementsByTagName("condition");
           Node cNode = cNodes.item(0);
           Element cElement = (Element)cNode;
           String data = cElement.getAttribute("data");    
           System.out.println("서울 현재 날씨 정보 : " + data);
           
           //문제) 내일, 모레 날씨 정보 출력
           NodeList fcNodes = root.getElementsByTagName("forecast_conditions");
           
           for(int i=1; i<3; i++)
           {
               Node fcNode = fcNodes.item(i);
               NodeList fcNodeChildNodes = fcNode.getChildNodes();
               
              for(int j=0; j<fcNodeChildNodes.getLength(); j++)
              {
                  Node fcNodeChildNode = fcNodeChildNodes.item(j);
                  Element fcElement = (Element)fcNodeChildNode;

                  if(fcNodeChildNode.getNodeName().equals("condition"))
                  {
                       data = fcElement.getAttribute("data");
                       if(i == 1)
                           System.out.println("서울의 내일 날씨 예보 : " + data);
                       else 
                           System.out.println("서울의 모레 날씨 예보 : " + data);
                  }
              }
           }

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}