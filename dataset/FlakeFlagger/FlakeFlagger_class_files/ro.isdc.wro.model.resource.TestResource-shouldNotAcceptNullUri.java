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
  @Test(expected=NullPointerException.class) public void shouldNotAcceptNullUri(){Resource.create(null,null);}
}
