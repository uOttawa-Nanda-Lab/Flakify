/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.extensions.processor.support;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.util.ObjectFactory;


/**
 * @author Alex Objelean
 */
public class TestObjectPoolHelper {
  @Test(expected = NullPointerException.class)
  public void cannotAcceptNullArgument()
      throws Exception {
    new ObjectPoolHelper<Void>(null);
  }
}
