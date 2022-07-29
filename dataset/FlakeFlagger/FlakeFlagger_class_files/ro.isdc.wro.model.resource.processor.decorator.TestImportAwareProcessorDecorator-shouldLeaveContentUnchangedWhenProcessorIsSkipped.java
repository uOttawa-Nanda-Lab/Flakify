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

import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.processor.ImportAware;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.support.ProcessingType;


/**
 * @author Alex Objelean
 */
public class TestImportAwareProcessorDecorator {
  @Mock
  private ResourcePreProcessor mockPreProcessor;
  @Mock
  private ResourcePostProcessor mockPostProcessor;
  private Reader mockReader;
  private Writer mockWriter;
  private ImportAwareProcessorDecorator victim;
  /**
   * A processor which is import-aware.
   */
  private static class ImportAwareProcessor
      implements ResourcePreProcessor, ResourcePostProcessor, ImportAware {
    public void process(final Reader reader, final Writer writer)
        throws IOException {
    }
    public void process(final Resource resource, final Reader reader, final Writer writer)
        throws IOException {
    }
    public boolean isImportAware() {
      return true;
    }
  }

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);
    mockReader = new StringReader("");
    mockWriter = new StringWriter();
  }

  private void initVictim() {
    InjectorBuilder.create(new BaseWroManagerFactory()).build().inject(victim);
  }

  @Test public void shouldLeaveContentUnchangedWhenProcessorIsSkipped() throws Exception{victim=new ImportAwareProcessorDecorator(mockPreProcessor,ProcessingType.IMPORT_ONLY);initVictim();final String resourceContent="var i      =     1;";final StringWriter writer=new StringWriter();victim.process(new StringReader(resourceContent),writer);Mockito.verify(mockPostProcessor,Mockito.never()).process(Mockito.any(Reader.class),Mockito.any(Writer.class));Assert.assertEquals(resourceContent,writer.toString());}
}
