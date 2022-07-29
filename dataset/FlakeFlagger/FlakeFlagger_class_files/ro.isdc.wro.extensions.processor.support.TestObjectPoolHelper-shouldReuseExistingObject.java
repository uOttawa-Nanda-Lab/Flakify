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
  @Test public void shouldReuseExistingObject() throws Exception{final ObjectPoolHelper<Integer> pool=new ObjectPoolHelper<Integer>(new ObjectFactory<Integer>(){@Override public Integer create(){return 3;}});final Integer object=pool.getObject();Assert.assertEquals(Integer.valueOf(3),object);pool.returnObject(object);}
}
