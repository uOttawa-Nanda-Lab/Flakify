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

  @Test public void testCRC32() throws Exception{final String input="testString";fingerprintCreator=new CRC32HashStrategy();final String hash=fingerprintCreator.getHash(new ByteArrayInputStream(input.getBytes()));assertEquals("18f4fd08",hash);}
}
