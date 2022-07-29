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

  @Test public void shouldHaveNoErrorWhenNoOptions() throws Exception{cssLint.setOptions("");final URL url=getClass().getResource("sample/content.css");cssLint.validate(IOUtils.toString(new FileInputStream(url.getFile())));}
}
