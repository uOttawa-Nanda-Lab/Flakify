package ro.isdc.wro.config.factory;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.jmx.ConfigConstants;

/**
 * @author Alex Objelean
 */
public class TestServletContextPropertyWroConfigurationFactory {
  @Mock
  private ServletContext mockServletContext;
  private ServletContextPropertyWroConfigurationFactory victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(mockServletContext.getResourceAsStream(Mockito.anyString())).thenReturn(new ByteArrayInputStream("".getBytes()));
    victim = new ServletContextPropertyWroConfigurationFactory(mockServletContext);
  }

  @Test public void shouldBuildDefaultConfigurationWhenConfigStreamIsNull(){Mockito.when(mockServletContext.getResourceAsStream(Mockito.anyString())).thenReturn(null);victim=new ServletContextPropertyWroConfigurationFactory(mockServletContext);Assert.assertNotNull(victim.create());}
}
