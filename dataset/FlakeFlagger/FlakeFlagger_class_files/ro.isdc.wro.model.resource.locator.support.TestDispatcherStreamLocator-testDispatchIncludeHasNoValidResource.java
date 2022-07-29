/*
 * Copyright (C) 2011. All rights reserved.
 */
package ro.isdc.wro.model.resource.locator.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestDispatcherStreamLocator {
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private RequestDispatcher mockDispatcher;
  @Mock
  private HttpServletResponse mockResponse;
  @Mock
  private UriLocator mockUriLocator;
  private DispatcherStreamLocator victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(mockRequest.getRequestURL()).thenReturn(new StringBuffer("/resource.js"));
    Mockito.when(mockRequest.getServletPath()).thenReturn("");
    Context.set(Context.standaloneContext());
    victim = new DispatcherStreamLocator();
    WroTestUtils.createInjector().inject(victim);
  }

  @Test(expected=IOException.class) public void testDispatchIncludeHasNoValidResource() throws Exception{when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockDispatcher);doAnswer(new Answer<Void>(){public Void answer(final InvocationOnMock invocation) throws Throwable{throw new IOException("Include doesn't work... nothing found");}}).when(mockDispatcher).include(Mockito.any(HttpServletRequest.class),Mockito.any(HttpServletResponse.class));victim.getInputStream(mockRequest,mockResponse,"/static/*.js");}
}
