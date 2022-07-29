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
  @Test public void shouldCreateResource(){Assert.assertNotNull(Resource.create("resource.js",ResourceType.JS));}
}
