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


  @Test public void splitingComplexOption(){final String option="predef=['YUI','window','document','OnlineOpinion','xui']";final String[] result=optionsBuilder.splitOptions(option);assertEquals(1,result.length);assertTrue(Arrays.equals(new String[]{option},result));}
}
