package ro.isdc.wro.config.support;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.concurrent.Callable;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestContextPropagatingCallable {
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletResponse mockResponse;
  @Mock
  private FilterConfig mockFilterConfig;
  private Context context;
  private static final Callable<Void> NO_OP_CALLABLE = new Callable<Void>() {
    public Void call()
        throws Exception {
      return null;
    }
  };

  @Before
  public void setUp() {
    initMocks(this);
    context = Context.webContext(mockRequest, mockResponse, mockFilterConfig);
  }

  @Test(expected=WroRuntimeException.class) public void shouldFailWhenNoContextIsAvailable(){Context.unset();new ContextPropagatingCallable<Void>(NO_OP_CALLABLE);}
}
