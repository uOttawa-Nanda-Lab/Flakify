/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.resource.locator.wildcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

/**
 * @author Alex Objelean
 */
public class TestWildcardContext {
  @Test public void shouldCreateContextWithNullArguments(){final WildcardContext context=new WildcardContext(null,null);assertNull(context.getUri());assertNull(context.getFolder());assertNull(context.getWildcard());}
}
