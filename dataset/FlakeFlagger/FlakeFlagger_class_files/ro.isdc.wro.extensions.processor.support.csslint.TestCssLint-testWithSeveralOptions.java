/**
 * Copyright wro4j@2011
 */
package ro.isdc.wro.extensions.processor.support.csslint;

import java.io.FileInputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestCssLint {
  private CssLint cssLint;

  @Before
  public void setUp() {
    cssLint = new CssLint();
  }

  @Test public void testWithSeveralOptions() throws Exception{cssLint.setOptions("1, 2");cssLint.validate("");}
}
