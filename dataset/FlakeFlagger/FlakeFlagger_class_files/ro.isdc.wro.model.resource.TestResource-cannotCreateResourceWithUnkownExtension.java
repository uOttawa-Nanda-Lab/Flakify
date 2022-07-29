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
  @Test(expected=IllegalArgumentException.class) public void cannotCreateResourceWithUnkownExtension(){Assert.assertNotNull(Resource.create("resource.coffee"));}
}
