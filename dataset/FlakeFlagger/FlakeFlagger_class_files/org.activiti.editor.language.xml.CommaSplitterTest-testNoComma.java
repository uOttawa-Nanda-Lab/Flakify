package org.activiti.editor.language.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.activiti.bpmn.converter.util.CommaSplitter;
import org.junit.Test;

/**

 */

public class CommaSplitterTest {

  @Test public void testNoComma(){String testString="Test String";List<String> result=CommaSplitter.splitCommas(testString);assertNotNull(result);assertEquals(1,result.size());assertEquals(testString,result.get(0));}
}
