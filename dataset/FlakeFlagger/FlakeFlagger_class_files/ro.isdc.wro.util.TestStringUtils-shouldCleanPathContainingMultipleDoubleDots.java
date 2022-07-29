/**
 *
 */
package ro.isdc.wro.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for {@link StringUtils} class.
 * @author Alex Objelean
 */
public class TestStringUtils {
  @Test public void shouldCleanPathContainingMultipleDoubleDots(){final String result=StringUtils.cleanPath("/a/b/../../d.txt");assertEquals("/d.txt",result);}
}
