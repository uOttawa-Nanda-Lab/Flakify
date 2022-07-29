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
  @Test(expected=NullPointerException.class) public void shouldNotAcceptNullResourceType(){Resource.create("resource.js",null);}
}
