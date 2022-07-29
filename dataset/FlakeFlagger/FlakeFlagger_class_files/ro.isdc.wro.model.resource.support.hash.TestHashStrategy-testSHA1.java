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

  @Test public void testSHA1() throws Exception{final String input="testString";fingerprintCreator=new SHA1HashStrategy();final String hash=fingerprintCreator.getHash(new ByteArrayInputStream(input.getBytes()));assertEquals("956265657d0b637ef65b9b59f9f858eecf55ed6a",hash);}
}
