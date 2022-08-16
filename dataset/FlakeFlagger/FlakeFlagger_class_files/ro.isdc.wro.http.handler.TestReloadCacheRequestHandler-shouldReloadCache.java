package ro.isdc.wro.http.handler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Ivar Conradi Østhus
 */
public class TestReloadCacheRequestHandler {
  private ReloadCacheRequestHandler victim;
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    victim = new ReloadCacheRequestHandler();
    
    Context.set(Context.webContext(request, response, mock(FilterConfig.class)));
    WroTestUtils.createInjector().inject(victim);
  }
  
  @Test public void shouldReloadCache() throws IOException,ServletException{victim.handle(request,response);verify(response,times(1)).setStatus(HttpServletResponse.SC_OK);}
}
