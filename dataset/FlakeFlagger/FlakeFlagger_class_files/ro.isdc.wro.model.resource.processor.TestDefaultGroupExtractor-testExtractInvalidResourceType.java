/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.model.group.DefaultGroupExtractor;
import ro.isdc.wro.model.resource.ResourceType;


/**
 * TestGroupsExtractor.
 *
 * @author Alex Objelean
 * @created Created on Nov 3, 2008
 */
public class TestDefaultGroupExtractor {
  private DefaultGroupExtractor victim;

  @Before
  public void init() {
    victim = new DefaultGroupExtractor();
  }

  @Test public void testExtractInvalidResourceType(){String uri="/test.js";ResourceType type=victim.getResourceType(mockRequestForUri(uri));Assert.assertEquals(ResourceType.JS,type);uri="/test.css";type=victim.getResourceType(mockRequestForUri(uri));Assert.assertEquals(ResourceType.CSS,type);uri="/test.txt";Assert.assertNull(victim.getResourceType(mockRequestForUri(uri)));}

  private HttpServletRequest mockRequestForUri(final String uri) {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn(uri);
    return request;
  }
}
