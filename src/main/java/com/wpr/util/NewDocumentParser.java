package com.wpr.util;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wpr.domain.ProcessDefinition;
import com.wpr.exception.DocumentParserException;



/**
 * 文件流程解析器
 * 
 * @author peirong.wpr
 * 
 */
public class NewDocumentParser {
	private static final String PROCESSOR_DEFINITION = "processor";
	private static final String STATE = "state";
	private static final String TRANSITION = "transition";

	private static final String ATTR_TYPE = "type";
	private static final String ATTR_DEFAULT = "defaultState";
	private static final String ATTR_DESC = "desc";

	private static final String ATTR_ID = "id";
	private static final String ATTR_ACTION = "target";
	private static final String ATTR_METHOD = "method";

	private static final String ATTR_RESULT = "result";
	private static final String ATTR_TO = "to";
	/**
	 * 用于解析xml文件
	 * @param in
	 * @return
	 * @throws DocumentParserException
	 * @throws MalformedURLException
	 * @throws DocumentException
	 */
	public static ProcessDefinition parse(File in) throws DocumentParserException, MalformedURLException, DocumentException {
		Document document = null;  
		SAXReader saxReader = new SAXReader();  
	    document = saxReader.read(in); // 读取XML文件,获得document对象
	    Element books=document.getRootElement();
	    
	    ProcessDefinition processDefinition = new ProcessDefinition();
		
	    return processDefinition;
	}
	public static void main(String[] args) throws MalformedURLException, DocumentParserException, DocumentException {
		String filePath = "workflow/test.xml";
		parse(new File(filePath));
	}
}
