/*
 * Copyright (C) 2010.
 * All rights reserved.
 */
package ro.isdc.wro.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ro.isdc.wro.model.resource.ResourceType;

/**
 * {@link CacheKey} test class
 *
 * @author Alex Objelean
 */
public class TestCacheKey {

  @Test public void testEquals2(){final CacheKey key1=new CacheKey("g1",ResourceType.JS,false);final CacheKey key2=new CacheKey("g1",ResourceType.JS,true);assertFalse(key1.equals(key2));}

  private CacheKey createValidCacheKey() {
    return new CacheKey("g1", ResourceType.JS, false);
  }
}
