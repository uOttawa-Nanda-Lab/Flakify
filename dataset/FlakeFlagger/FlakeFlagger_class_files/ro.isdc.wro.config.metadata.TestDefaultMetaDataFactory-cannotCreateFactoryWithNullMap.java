package ro.isdc.wro.config.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestDefaultMetaDataFactory {
  private MetaDataFactory victim;

  @Before
  public void setUp() {
    victim = new DefaultMetaDataFactory();
  }

  @Test(expected=NullPointerException.class) public void cannotCreateFactoryWithNullMap(){new DefaultMetaDataFactory(null);}
}
