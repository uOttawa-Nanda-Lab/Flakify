/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.extensions.processor.support.linter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;


/**
 * @author Alex Objelean
 */
public class TestOptionsBuilder {
  private final OptionsBuilder optionsBuilder = new OptionsBuilder();


  @Test public void splitingComplexOptions(){final String option="option1,option2,option3=['YUI','window','document','xui'],option4,option5=['YUI','xui'],option6";final String[] result=optionsBuilder.splitOptions(option);assertEquals(6,result.length);assertEquals(Arrays.toString(new String[]{"option1","option2","option3=['YUI','window','document','xui']","option4","option5=['YUI','xui']","option6"}),Arrays.toString(result));}
}
