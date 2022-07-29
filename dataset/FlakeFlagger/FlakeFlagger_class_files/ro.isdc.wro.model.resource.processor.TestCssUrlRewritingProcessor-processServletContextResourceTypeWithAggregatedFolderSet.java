/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.processor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.group.Inject;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.processor.impl.css.CssUrlRewritingProcessor;
import ro.isdc.wro.model.resource.support.DefaultResourceAuthorizationManager;
import ro.isdc.wro.model.resource.support.ResourceAuthorizationManager;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test for {@link CssUrlRewritingProcessor} class.
 *
 * @author Alex Objelean
 * @created Created on Nov 3, 2008
 */
public class TestCssUrlRewritingProcessor {
  private static final Logger LOG = LoggerFactory.getLogger(TestCssUrlRewritingProcessor.class);
  private CssUrlRewritingProcessor processor;

  private static final String CSS_INPUT_NAME = "cssUrlRewriting.css";


  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    processor = new CssUrlRewritingProcessor() {
      @Inject
      private ResourceAuthorizationManager authorizationManager;
      @Override
      protected void onProcessCompleted() {
        if (authorizationManager instanceof DefaultResourceAuthorizationManager) {
          LOG.debug("allowed urls: {}", ((DefaultResourceAuthorizationManager) authorizationManager).list());
        }
      }
      @Override
      protected String getUrlPrefix() {
        return "[WRO-PREFIX]?id=";
      }
    };
    WroTestUtils.createInjector().inject(processor);
  }

  /**
   * @param resourceUri the resource should return.
   * @return mocked {@link Resource} object.
   */
  private Resource createMockResource(final String resourceUri) {
    final Resource resource = Mockito.mock(Resource.class);
    Mockito.when(resource.getUri()).thenReturn(resourceUri);
    return resource;
  }


  /**
 * Test a servletContext css resource.
 */@Test public void processServletContextResourceTypeWithAggregatedFolderSet() throws IOException{Context.get().setAggregatedFolderPath("wro/css");WroTestUtils.compareProcessedResourceContents("classpath:" + CSS_INPUT_NAME,"classpath:cssUrlRewriting-servletContext-aggregatedFolderSet-outcome.css",new ResourcePostProcessor(){public void process(final Reader reader,final Writer writer) throws IOException{processor.process(createMockResource("/static/img/" + CSS_INPUT_NAME),reader,writer);}});}
}
