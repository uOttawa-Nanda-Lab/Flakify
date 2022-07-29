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
  @Test(expected=NullPointerException.class) public void cannotReturnNullObjectToPool() throws Exception{final ObjectPoolHelper<Integer> pool=new ObjectPoolHelper<Integer>(new ObjectFactory<Integer>(){@Override public Integer create(){return null;}});final Integer object=pool.getObject();pool.returnObject(object);}
}
