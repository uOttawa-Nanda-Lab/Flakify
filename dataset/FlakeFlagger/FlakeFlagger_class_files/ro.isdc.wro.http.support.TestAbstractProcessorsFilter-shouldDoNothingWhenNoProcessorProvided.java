package ro.isdc.wro.http.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;


/**
 * Test the behavior of {@link AbstractProcessorsFilter}
 *
 * @author Alex Objelean
 */
public class TestAbstractProcessorsFilter {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private FilterChain chain;
  private ByteArrayOutputStream outputStream;
  private AbstractProcessorsFilter victim;

  @Before
  public void setUp() throws Exception {
    outputStream = new ByteArrayOutputStream();;
    MockitoAnnotations.initMocks(this);
    Mockito.when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(outputStream));
  }

  @Test public void shouldDoNothingWhenNoProcessorProvided() throws Exception{doFilterWithProcessors(Collections.<ResourcePreProcessor>emptyList());Assert.assertEquals(0,outputStream.size());}

  private void doFilterWithProcessors(final List<ResourcePreProcessor> processors)
      throws Exception {
    victim = new AbstractProcessorsFilter() {
      @Override
      protected List<ResourcePreProcessor> getProcessorsList() {
        return processors;
      }
      @Override
      protected void onRuntimeException(final RuntimeException e, final HttpServletResponse response, final FilterChain chain) {
        throw e;
      }
    };
   victim.doFilter(request, response, chain);
  }
}
