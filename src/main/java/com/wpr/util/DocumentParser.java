package com.wpr.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.wpr.domain.Process;
import com.wpr.domain.ProcessDefinition;
import com.wpr.domain.State;
import com.wpr.exception.DocumentParserException;


/**
 * 文件流程解析器
 * 
 * @author peirong.wpr
 * 
 */
public class DocumentParser {
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
	 * @throws ParserConfigurationException 为了简单，所有的异常都抛出了
	 * @throws SAXException
	 * @throws IOException
	 * @throws DocumentParserException
	 */
	public static Result<Void> parse(InputStream in) throws ParserConfigurationException, SAXException, IOException, DocumentParserException {
		ProcessDefinition processDefinition = new ProcessDefinition();
		Result<Process> result = null;
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		// 用于忽略xml中的dtd
		docBuilder.setEntityResolver(new EntityResolver() {

			public InputSource resolveEntity(String publicId,
					String systemId) throws SAXException, IOException {
				String tmp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
				return new InputSource(new ByteArrayInputStream(tmp.getBytes()));
			}
		});
		Document xmlDoc = docBuilder.parse(in);
		//开始解析根节点
		Node procDefNode = xmlDoc
				.getElementsByTagName(PROCESSOR_DEFINITION).item(0);
		NamedNodeMap procDefAttrMap = procDefNode.getAttributes();
		if (StringUtils.isBlank(procDefAttrMap.getNamedItem(ATTR_TYPE)
				.getNodeValue())) {
			throw new DocumentParserException();
		}
		if (StringUtils.isBlank(procDefAttrMap.getNamedItem(ATTR_DEFAULT)
				.getNodeValue())) {
			throw new DocumentParserException();
		}
		processDefinition.setType(procDefAttrMap.getNamedItem(ATTR_TYPE).getNodeValue());
		processDefinition.setDesc(procDefAttrMap.getNamedItem(ATTR_TYPE).getNodeValue());
		processDefinition.setDefaultState(procDefAttrMap.getNamedItem(ATTR_DEFAULT).getNodeValue());
		NodeList nodeList = procDefNode.getChildNodes();
		List<State> stateList = new ArrayList<State>();
		for(int i = 0; i < nodeList.getLength(); i++){
			Node stateNode = nodeList.item(i);
			if (!stateNode.getNodeName().equals(TRANSITION))
				continue;
			stateList.add(parseStateNode(stateNode));
		}
		
		return null;
	}
	/**
	 * 解析State节点
	 * @param stateNode
	 * @return
	 * @throws DocumentParserException 
	 */
	private static State parseStateNode(Node stateNode) throws DocumentParserException {
		if (!STATE.equals(stateNode.getNodeName())){
			throw new DocumentParserException();
		}
		State state = new State();
		NamedNodeMap transAttrMap = stateNode.getAttributes();
		if (transAttrMap.getNamedItem(ATTR_ACTION) == null
				|| transAttrMap.getNamedItem(ATTR_ID) == null
				|| transAttrMap.getNamedItem(ATTR_METHOD) == null) {
			throw new DocumentParserException();
		}
		state.setTarget(transAttrMap.getNamedItem(ATTR_ACTION)
				.getNodeValue());
		state.setId(transAttrMap.getNamedItem(ATTR_ID).getNodeValue());
		state.setMethod(transAttrMap.getNamedItem(ATTR_METHOD)
				.getNodeValue());
		return null;
	}

	public static class ByteArrayInputStream extends InputStream {
		private byte[] buffer;
		private int index;
		private int limit;
		private int mark;

		private boolean closed;

		public ByteArrayInputStream(byte[] data) {
			this(data, 0, data.length);
		}

		public ByteArrayInputStream(byte[] data, int offset, int length) {
			if (data == null) {
				throw new NullPointerException();
			} else if (offset < 0 || offset + length > data.length
					|| length < 0) {
				throw new IndexOutOfBoundsException();
			} else {
				buffer = data;
				index = offset;
				limit = offset + length;
				mark = offset;
			}
		}

		@Override
		public int read() throws IOException {
			if (closed) {
				throw new IOException("Stream closed");
			} else if (index >= limit) {
				return -1; // EOF
			} else {
				return buffer[index++] & 0xff;
			}
		}
	}
}
