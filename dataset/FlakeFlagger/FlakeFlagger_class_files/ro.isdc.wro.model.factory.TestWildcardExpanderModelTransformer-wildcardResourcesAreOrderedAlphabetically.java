/*
 * Copyright (C) 2011 . All rights reserved.
 */
package ro.isdc.wro.model.factory;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.transformer.WildcardExpanderModelTransformer;
import ro.isdc.wro.util.Function;
import ro.isdc.wro.util.WroUtil;


/**
 * @author Alex Objelean
 */
public class TestWildcardExpanderModelTransformer {
  private static final Logger LOG = LoggerFactory.getLogger(TestWildcardExpanderModelTransformer.class);
  private WildcardExpanderModelTransformer transformer;
  @Mock
  private WroModelFactory decoratedFactory;
  @Mock
  private ProcessorsFactory processorsFactory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Context.set(Context.standaloneContext());
    transformer = new WildcardExpanderModelTransformer();
    // create manager to force correct initialization.
    final BaseWroManagerFactory factory = new BaseWroManagerFactory();
    factory.setProcessorsFactory(processorsFactory);
    factory.addModelTransformer(transformer);
    final Injector injector = InjectorBuilder.create(factory).build();
    injector.inject(transformer);
  }

  @Test public void wildcardResourcesAreOrderedAlphabetically(){final WroModel model=new WroModel();final String uri=String.format(ClasspathUriLocator.PREFIX + "%s/expander/order/**.js",WroUtil.toPackageAsFolder(getClass()));model.addGroup(new Group("group").addResource(Resource.create(uri,ResourceType.JS)));Mockito.when(decoratedFactory.create()).thenReturn(model);final WroModel changedModel=transformer.transform(model);LOG.debug("model: {}",changedModel);Assert.assertEquals(7,changedModel.getGroupByName("group").getResources().size());final List<Resource> resources=changedModel.getGroupByName("group").getResources();Assert.assertEquals("01-xyc.js",FilenameUtils.getName(resources.get(0).getUri()));Assert.assertEquals("02-xyc.js",FilenameUtils.getName(resources.get(1).getUri()));Assert.assertEquals("03-jquery-ui.js",FilenameUtils.getName(resources.get(2).getUri()));Assert.assertEquals("04-xyc.js",FilenameUtils.getName(resources.get(3).getUri()));Assert.assertEquals("05-xyc.js",FilenameUtils.getName(resources.get(4).getUri()));Assert.assertEquals("06-xyc.js",FilenameUtils.getName(resources.get(5).getUri()));Assert.assertEquals("07-jquery-impromptu.js",FilenameUtils.getName(resources.get(6).getUri()));}
}
