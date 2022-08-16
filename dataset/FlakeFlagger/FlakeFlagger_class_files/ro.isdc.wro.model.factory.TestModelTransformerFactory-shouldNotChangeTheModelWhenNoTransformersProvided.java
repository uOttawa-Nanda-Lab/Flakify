/*
 * Copyright (C) 2011. All rights reserved.
 */
package ro.isdc.wro.model.factory;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.util.Transformer;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestModelTransformerFactory {
  @Mock
  private WroModelFactory mockFactory;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);
    Mockito.when(mockFactory.create()).thenReturn(new WroModel());
  }

  private ModelTransformerFactory factory;

  @Test public void shouldNotChangeTheModelWhenNoTransformersProvided(){factory=new ModelTransformerFactory(mockFactory);Assert.assertEquals(new WroModel().getGroups(),factory.create().getGroups());}
}
