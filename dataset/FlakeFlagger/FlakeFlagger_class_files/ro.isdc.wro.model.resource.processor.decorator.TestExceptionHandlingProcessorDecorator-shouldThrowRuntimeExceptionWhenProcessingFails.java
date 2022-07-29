package ro.isdc.wro.model.resource.processor.decorator;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestExceptionHandlingProcessorDecorator {
  @Mock
  private ResourcePreProcessor mockProcessor;
  @Mock
  private Resource mockResource;
  private ExceptionHandlingProcessorDecorator victim;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);
    victim = new ExceptionHandlingProcessorDecorator(mockProcessor);
    WroTestUtils.createInjector().inject(victim);
  }

  @Test(expected=WroRuntimeException.class) public void shouldThrowRuntimeExceptionWhenProcessingFails() throws Exception{Mockito.doThrow(new IOException("BOOM")).when(mockProcessor).process(Mockito.any(Resource.class),Mockito.any(Reader.class),Mockito.any(Writer.class));victim.process(mockResource,new StringReader(""),new StringWriter());}
}
