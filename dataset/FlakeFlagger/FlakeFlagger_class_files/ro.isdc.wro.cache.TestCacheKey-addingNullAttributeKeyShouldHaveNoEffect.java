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

  private CacheKey createValidCacheKey() {
    return new CacheKey("g1", ResourceType.JS, false);
  }

  @Test public void addingNullAttributeKeyShouldHaveNoEffect(){final CacheKey key=createValidCacheKey().addAttribute(null,"");assertEquals(key,createValidCacheKey());}
}
