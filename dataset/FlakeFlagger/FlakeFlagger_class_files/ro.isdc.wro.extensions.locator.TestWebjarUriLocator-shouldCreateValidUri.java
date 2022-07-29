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

  @Test public void shouldCreateValidUri(){assertEquals("webjar:/path/to/resource.js",WebjarUriLocator.createUri("/path/to/resource.js"));}

  private void assertNotEmpty(final InputStream stream)
      throws IOException {
    IOUtils.read(stream, new byte[] {});
    stream.close();
  }
}
