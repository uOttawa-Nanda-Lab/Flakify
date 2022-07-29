/*
* Copyright 2011 wro4j
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package ro.isdc.wro.extensions.model.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Inject;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;

/**
 * @author Alex Objelean
 * @created 6 Aug 2011
 */
public class TestSmartWroModelFactory {
  private SmartWroModelFactory factory;
  private Injector injector;
  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    injector = InjectorBuilder.create(new BaseWroManagerFactory()).build();
  }

  /**
   * Creates a {@link SmartWroModelFactory} which is provided with a list of two {@link WroModelFactory}'s. The first
   * one is failing, the second one is working.
   */
  private SmartWroModelFactory createTestSmartModelFactory() {
    final WroModelFactory failingModelFactory = new WroModelFactory() {
      @Override
      public WroModel create() {
        throw new WroRuntimeException("Cannot create model", new IOException("invalid model stream"));
      }

      @Override
      public void destroy() {}
    };
    final WroModelFactory workingModelFactory = new WroModelFactory() {
      @Override
      public WroModel create() {
        return new WroModel();
      }

      @Override
      public void destroy() {}
    };
    final SmartWroModelFactory factory = new SmartWroModelFactory() {
      @Override
      protected List<WroModelFactory> newWroModelFactoryFactoryList() {
        final List<WroModelFactory> list = new ArrayList<WroModelFactory>();
        list.add(failingModelFactory);
        list.add(workingModelFactory);
        return list;
      }
    };
    injector.inject(factory);
    return factory;
  }

  /**
 * Checks that modelFactories provided as a list will have all required fields injected (ex: locatorFactory)
 */@Test public void shouldInjectInnerModelFactories(){final SmartWroModelFactory factory=new SmartWroModelFactory(){@Override protected List<WroModelFactory> newWroModelFactoryFactoryList(){final List<WroModelFactory> list=new ArrayList<WroModelFactory>();list.add(new CustomWroModel(){@Override public WroModel create(){Assert.assertNotNull("Should have an injected locator!",uriLocatorFactory);return new WroModel();}});return list;}};injector.inject(factory);Assert.assertNotNull(factory.create());}

  private static class CustomWroModel implements WroModelFactory {
    @Inject
    UriLocatorFactory uriLocatorFactory;
    @Override
    public WroModel create() {
      return null;
    }
    @Override
    public void destroy() {
    }
  }
}
