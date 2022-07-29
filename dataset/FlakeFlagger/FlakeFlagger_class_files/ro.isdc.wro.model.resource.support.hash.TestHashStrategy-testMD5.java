/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.resource.support.hash;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;

/**
 * Test class for {@link HashStrategy}
 *
 * @author Alex Objelean
 * @created 15 Aug 2010
 */
public class TestHashStrategy {
  private HashStrategy fingerprintCreator;

  @Test public void testMD5() throws Exception{final String input="testString";fingerprintCreator=new MD5HashStrategy();final String hash=fingerprintCreator.getHash(new ByteArrayInputStream(input.getBytes()));assertEquals("536788f4dbdffeecfbb8f350a941eea3",hash);}
}
