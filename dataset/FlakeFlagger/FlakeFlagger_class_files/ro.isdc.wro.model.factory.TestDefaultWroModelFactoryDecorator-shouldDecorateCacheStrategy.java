package ro.isdc.wro.model.factory;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.util.ObjectDecorator;
import ro.isdc.wro.util.Transformer;

/**
 * @author Alex Objelean
 */
public class TestDefaultWroModelFactoryDecorator {
  private final List<Transformer<WroModel>> emptyTransformers = Collections.emptyList();
  private WroModelFactory victim;

  @Test
  public void shouldDecorateCacheStrategy() {
    final WroModelFactory original = new XmlModelFactory();
    victim = DefaultWroModelFactoryDecorator.decorate(original, emptyTransformers);
    Assert.assertTrue(victim instanceof DefaultWroModelFactoryDecorator);
    Assert.assertSame(original, ((ObjectDecorator<?>) victim).getDecoratedObject());
  }
}
