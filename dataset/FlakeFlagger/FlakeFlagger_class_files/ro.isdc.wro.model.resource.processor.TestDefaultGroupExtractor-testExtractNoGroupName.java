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

  @Test public void testExtractNoGroupName(){String groupName=victim.getGroupName(mockRequestForUri("/app/test.js"));Assert.assertEquals("test",groupName);groupName=victim.getGroupName(mockRequestForUri("/app/test.group.js"));Assert.assertEquals("test.group",groupName);Assert.assertEquals(null,victim.getGroupName(mockRequestForUri("/123/")));}

  private HttpServletRequest mockRequestForUri(final String uri) {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn(uri);
    return request;
  }
}
