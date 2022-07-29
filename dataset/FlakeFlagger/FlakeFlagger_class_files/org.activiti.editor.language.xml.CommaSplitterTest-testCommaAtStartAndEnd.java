package org.activiti.editor.language.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.activiti.bpmn.converter.util.CommaSplitter;
import org.junit.Test;

/**

 */

public class CommaSplitterTest {

  @Test public void testCommaAtStartAndEnd(){String testString=",first,second,";List<String> result=CommaSplitter.splitCommas(testString);assertNotNull(result);assertEquals(2,result.size());assertEquals("first",result.get(0));assertEquals("second",result.get(1));}
}
