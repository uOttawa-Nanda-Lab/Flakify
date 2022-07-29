package org.activiti.editor.language.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.activiti.bpmn.converter.util.CommaSplitter;
import org.junit.Test;

/**

 */

public class CommaSplitterTest {

  @Test public void testOneComa(){String testString="Test,String";List<String> result=CommaSplitter.splitCommas(testString);assertNotNull(result);assertEquals(2,result.size());assertEquals("Test",result.get(0));assertEquals("String",result.get(1));}
}
