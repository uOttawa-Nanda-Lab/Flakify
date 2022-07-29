package ro.isdc.wro.util.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.model.resource.processor.support.ProcessorProvider;
import ro.isdc.wro.model.resource.support.hash.HashStrategyProvider;
import ro.isdc.wro.model.resource.support.naming.NamingStrategyProvider;
import ro.isdc.wro.util.Ordered;

/**
 * @author Alex Objelean
 */
public class TestProviderFinder {
  private ProviderFinder<?> victim;

  @Test public void shouldFindProcessorsProvider(){victim=ProviderFinder.of(ProcessorProvider.class);assertFalse(victim.find().isEmpty());}

  private static class OrderedProvider implements Ordered {
    private final int providerPriority;

    public OrderedProvider(final int providerPriority) {
      this.providerPriority = providerPriority;
    }

    public int getOrder() {
      return providerPriority;
    }
  }
}
