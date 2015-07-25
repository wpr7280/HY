package com.wpr.util;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wpr.domain.ProcessDefinition;
import com.wpr.domain.State;
import com.wpr.domain.Transition;
import com.wpr.exception.DocumentParserException;



/**
 * 文件流程解析器
 * 
 * @author peirong.wpr
 * 
 */
@SuppressWarnings("deprecation")
public class NewDocumentParser {
	private static final String PROCESSOR_DEFINITION = "processor";
	private static final String STATE = "state";
	private static final String TRANSITION = "transition";

	private static final String ATTR_TYPE = "type";
	private static final String ATTR_DEFAULT = "defaultState";
	private static final String ATTR_DESC = "desc";

	private static final String STATE_ATTR_ID = "id";
	private static final String STATE_ATTR_TARGET = "target";
	private static final String STATE_ATTR_METHOD = "method";

	private static final String TRANSITION_ATTR_RESULT = "result";
	private static final String TRANSITION_ATTR_TO = "to";
	/**
	 * 解析xml文件
	 * @param procXmlFile
	 * @return
	 * @throws Exception
	 */
	public static ProcessDefinition parser(String procXmlFile) throws Exception{
		return parser(new File(procXmlFile));
	}
	/**
	 * 用于解析xml文件
	 * @param in
	 * @return
	 * @throws DocumentParserException
	 * @throws MalformedURLException
	 * @throws DocumentException
	 */
	public static ProcessDefinition parser(File in) throws DocumentParserException, MalformedURLException, DocumentException {
		Document document = null;  
		SAXReader saxReader = new SAXReader();  
	    document = saxReader.read(in); // 读取XML文件,获得document对象
	    
	    Element processorNode=document.getRootElement();
	    Assert.assertNotNull(processorNode);
	    Assert.assertEquals(PROCESSOR_DEFINITION, processorNode.getName());
	    Assert.assertNotNull(processorNode.attributeValue(ATTR_TYPE));
	    Assert.assertNotNull(processorNode.attributeValue(ATTR_DEFAULT));

	    ProcessDefinition processDefinition = new ProcessDefinition();
	    Map<String,State> states = new HashMap<String, State>();
	    
	    processDefinition.setType(processorNode.attributeValue(ATTR_TYPE));
	    processDefinition.setDefaultState(processorNode.attributeValue(ATTR_DEFAULT));
	    processDefinition.setDesc(processorNode.attributeValue(ATTR_DESC));
	    
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = processorNode.elementIterator(STATE);
	    while(iter.hasNext()){
	    	Element stateNode = iter.next();
//	    	System.out.println(stateNode.attributeValue("id"));
	    	states.put(stateNode.getName(), parserState(stateNode));
	    }
	    processDefinition.setStates(states);
	    return processDefinition;
	}
	/**
	 * 解析xml文件中的state节点
	 * @param stateNode
	 * @return
	 */
	private static State parserState(Element stateNode) {
		Assert.assertNotNull(stateNode.attributeValue(STATE_ATTR_TARGET));
		Assert.assertNotNull(stateNode.attributeValue(STATE_ATTR_METHOD));
		Assert.assertNotNull(stateNode.attributeValue(STATE_ATTR_ID));
		
		State state = new State();
		state.setId(stateNode.attributeValue(STATE_ATTR_ID));
		state.setMethod(stateNode.attributeValue(STATE_ATTR_METHOD));
		state.setTarget(stateNode.attributeValue(STATE_ATTR_TARGET));
		
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = stateNode.elementIterator(TRANSITION);
		List<Transition> transitionList = new ArrayList<Transition>();
		while(iter.hasNext()){
			Element transitionElement =iter.next();
			transitionList.add(parserTransition(transitionElement));
//			System.out.println("\t"+transitionElement.attributeValue("result"));
		}
		state.setTransitions(transitionList);
		return state;
	}
	/**
	 * 解析transition节点
	 * @param next
	 * @return
	 */
	private static Transition parserTransition(Element next) {
		if(next==null)
			return null;
		Assert.assertNotNull(next.attributeValue(TRANSITION_ATTR_RESULT));
		Assert.assertNotNull(next.attributeValue(TRANSITION_ATTR_TO));
		
		Transition transition= new Transition();
		transition.setResult(next.attributeValue(TRANSITION_ATTR_RESULT));
		transition.setTo(next.attributeValue(TRANSITION_ATTR_TO));
		return transition;
	}
	
	public static void main(String[] args) throws MalformedURLException, DocumentParserException, DocumentException {
		String filePath = "D:/test.xml";
		System.out.println(parser(new File(filePath)));
	}

}
