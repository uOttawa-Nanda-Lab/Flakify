package ro.isdc.wro.http;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.DefaultWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.util.AbstractDecorator;


/**
 * Test {@link WroServletContextListener} class.
 *
 * @author Alex Objelean
 */
public class TestWroServletContextListener {
  @Mock
  private ServletContextEvent mockServletContextEvent;
  private final Map<String, Object> map = new HashMap<String, Object>();
  @Mock
  private ServletContext mockServletContext;
  private WroServletContextListener victim;

  @Before
  public void setUp() {
    initMocks(this);
    map.clear();

    when(mockServletContextEvent.getServletContext()).thenReturn(mockServletContext);
    when(mockServletContext.getAttribute(Mockito.anyString())).then(new Answer<Object>() {
      public Object answer(final InvocationOnMock invocation)
          throws Throwable {
        return map.get(invocation.getArguments()[0]);
      }
    });
    Mockito.doAnswer(new Answer<Object>() {
      public Object answer(final InvocationOnMock invocation)
          throws Throwable {
        final String key = (String) invocation.getArguments()[0];
        final Object value = invocation.getArguments()[1];
        return map.put(key, value);
      }
    }).when(mockServletContext).setAttribute(Mockito.anyString(), Mockito.anyObject());
    Mockito.doAnswer(new Answer<Object>() {
      public Object answer(final InvocationOnMock invocation)
          throws Throwable {
        final Object value = invocation.getArguments()[0];
        return map.remove(value);
      }
    }).when(mockServletContext).removeAttribute(Mockito.anyString());
    victim = new WroServletContextListener();
  }

  @Test(expected=NullPointerException.class) public void cannotSetNullWroManager(){victim.setManagerFactory(null);}
}
