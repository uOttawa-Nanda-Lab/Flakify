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
  @Test public void shouldCreateResourceWithCorrectType(){Resource resource=Resource.create("resource.js");Assert.assertNotNull(resource);Assert.assertEquals(ResourceType.JS,resource.getType());resource=Resource.create("resource.css");Assert.assertNotNull(resource);Assert.assertEquals(ResourceType.CSS,resource.getType());}
}
