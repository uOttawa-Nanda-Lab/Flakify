package ro.isdc.wro.extensions.processor;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;


/**
 * @author Alex Objelean
 */
public class TestPathPatternProcessorDecorator {
  private PathPatternProcessorDecorator victim;
  @Mock
  private Reader mockReader;
  @Mock
  private Writer mockWriter;
  @Mock
  private ResourcePreProcessor mockProcessor;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockReader = new StringReader("") {
      @Override
      public void close() {
        //make reader uncloseable
      }
    };
  }
  
  @Test public void shouldApplyPatternsWhenExcludeNotMatches() throws Exception{checkThatExclusionNotMatches("/a/path/to.js","/**/*.less");checkThatExclusionNotMatches("/b/path/to.css","/a/*/to.*");checkThatExclusionNotMatches("/a/path/inner/bo.css","/a/**/to.*");checkThatExclusionNotMatches("/a/b/c/aame.css","/a/**/n?me.css");checkThatExclusionNotMatches("/a/b/c/name.less","/a/**/n?me.js","/a/**/n?me.c?s");}

  private void checkThatInclusionMatches(final String resourceUri, final String... patterns)
      throws IOException {
    victim = PathPatternProcessorDecorator.include(mockProcessor, patterns);
    final Resource resource = Resource.create(resourceUri, ResourceType.CSS);
    victim.process(resource, mockReader, mockWriter);
    Mockito.verify(mockProcessor, Mockito.times(1)).process(resource, mockReader, mockWriter);
    //reset for correct times computation
    Mockito.reset(mockProcessor);
  }
  
  private void checkThatExclusionMatches(final String resourceUri, final String... patterns)
      throws IOException {
    victim = PathPatternProcessorDecorator.exclude(mockProcessor, patterns);
    final Resource resource = Resource.create(resourceUri, ResourceType.CSS);
    victim.process(resource, mockReader, mockWriter);
    Mockito.verify(mockProcessor, Mockito.never()).process(resource, mockReader, mockWriter);
  }
  
  private void checkThatInclusionNotMatches(final String resourceUri, final String... patterns)
      throws IOException {
    victim = PathPatternProcessorDecorator.include(mockProcessor, patterns);
    Resource resource = Resource.create(resourceUri, ResourceType.CSS);
    victim.process(resource, mockReader, mockWriter);
    Mockito.verify(mockProcessor, Mockito.never()).process(resource, mockReader, mockWriter);
  }
  
  private void checkThatExclusionNotMatches(final String resourceUri, final String... patterns)
      throws IOException {
    victim = PathPatternProcessorDecorator.exclude(mockProcessor, patterns);
    Resource resource = Resource.create(resourceUri, ResourceType.CSS);
    victim.process(resource, mockReader, mockWriter);
    Mockito.verify(mockProcessor, Mockito.times(1)).process(resource, mockReader, mockWriter);
    //reset for correct times computation
    Mockito.reset(mockProcessor);
  }
  
}
