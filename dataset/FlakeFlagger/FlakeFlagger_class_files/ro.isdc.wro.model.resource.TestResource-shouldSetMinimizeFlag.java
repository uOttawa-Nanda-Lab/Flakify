/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.resource;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestResource {
  @Test public void shouldSetMinimizeFlag(){final Resource resource=Resource.create("resource.js");resource.setMinimize(true);Assert.assertTrue(resource.isMinimize());resource.setMinimize(false);Assert.assertFalse(resource.isMinimize());}
}
