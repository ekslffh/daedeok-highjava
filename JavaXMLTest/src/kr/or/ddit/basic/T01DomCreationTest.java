package kr.or.ddit.basic;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 XML 문서 생성 예제
 * @author PC-20
 */
public class T01DomCreationTest {
/*
 	W3C(World Wide Web Consortium) DOM(Document Object Model)표준에 대하여...
 	
 	DOM은 문서에 접근하는 표준방법으로서, 이를 이용하면 플랫폼 및 개발언어에 독립적으로
 	문서의 내용, 구조 및 스타일 정보를 동적으로 핸들링(생성, 접근, 수정, 삭제등) 할수 있다.
 	
 	W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
 	
 	Core DOM - 모든 문서타입을 위한 핵심 표준 모델(API)
 	XML DOM - XML문서를 위한 표준 모델
 	HTML DOM - HTML문서를 위한 표준 모델
 	
 	ex) HTML DOM은 HTML 엘리먼트 요소 및 속성정보를 핸들링하기 위한 인터페이스를 제공한다.
*/
	
	public void createDoc() throws ParserConfigurationException, TransformerException, FileNotFoundException, IOException {
		
		// XML문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		// Document 객체 생성
		Document document = builder.newDocument();
		
		// root 엘리먼트 생성
		Element root = document.createElement("root");
		
		// booklist 엘리먼트 생성
		Element booklist = document.createElement("booklist");
		
		// book 엘리먼트 생성 및 속성값 설정하기
		Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");
		
		// 자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급");
		Element author = document.createElement("author");
		author.setTextContent("이순신");
		Element price = document.createElement("price");
		price.setTextContent("25000");
		
		// book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		// booklist 엘리먼트에 book 자식 엘리먼트 추가
		booklist.appendChild(book);
		//------------------------------------------------
		
		// book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");
		
		// 자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바중급");
		author = document.createElement("author");
		author.setTextContent("홍길동");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		// book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		// booklist 엘리먼트에 book 자식 엘리먼트 추가
		booklist.appendChild(book);
		//------------------------------------------------
		
		// book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");
		
		// 자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("데이터베이스초급");
		author = document.createElement("author");
		author.setTextContent("성춘향");
		price = document.createElement("price");
		price.setTextContent("35000");
		
		// book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		// booklist 엘리먼트에 book 자식 엘리먼트 추가
		booklist.appendChild(book);
		//------------------------------------------------
		
		// book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");
		
		// 자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("데이터베이스고급");
		author = document.createElement("author");
		author.setTextContent("배소현");
		price = document.createElement("price");
		price.setTextContent("45000");
		
		// book 엘리먼트에 자식 엘리먼트 추가
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		// booklist 엘리먼트에 book 자식 엘리먼트 추가
		booklist.appendChild(book);
		//------------------------------------------------
		
		// 루트 엘리먼트에 booklist 추가하기
		root.appendChild(booklist);
		
		// 도큐먼트에 root 엘리먼트 추가하기
		document.appendChild(root);
		
		// XML 문서 변환기 생성하기
		TransformerFactory transformerFactory = 
				TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		// 출력 인코딩 정보 설정
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		// 들여쓰기 설정(공백 크기: 2)
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		
		// XML 문서 변환작업 하기
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult();
		
		// 1. 바이트 배열에 문서내용 저장하는 경우...
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		result.setOutputStream(baos);
		
		transformer.transform(source, result); // 변환시작...
		
		System.out.println(
				new String(baos.toByteArray(), StandardCharsets.UTF_8));
		
		// 2. 파일에 문서를 저장하는 경우...
		try (FileOutputStream fos = new FileOutputStream("./src/new_book.xml")) {
			result.setOutputStream(fos);
			transformer.transform(source, result); // 변환시작
		} 
		
	}
	
	public static void main(String[] args) throws Exception {
		new T01DomCreationTest().createDoc();
	}
}
