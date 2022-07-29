package ro.isdc.wro.extensions.locator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.model.resource.locator.UriLocator;


/**
 * @author Alex Objelean
 */
public class TestWebjarUriLocator {
  private UriLocator victim;

  @Before
  public void setUp() {
    victim = new WebjarUriLocator();
  }

  @Test public void shouldFindValidWebjar() throws Exception{assertNotEmpty(victim.locate("webjar:jquery.js"));assertNotEmpty(victim.locate("webjar:jquery/2.0.0/jquery.js"));assertNotEmpty(victim.locate("webjar:/jquery/2.0.0/jquery.js"));}

  private void assertNotEmpty(final InputStream stream)
      throws IOException {
    IOUtils.read(stream, new byte[] {});
    stream.close();
  }
}
