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

  @Test public void shouldReturnExistingKey(){final Map<String, Object> map=new HashMap<String, Object>();map.put("key","value");victim=new DefaultMetaDataFactory(map);assertEquals(map,victim.create());assertEquals("value",victim.create().get("key"));}
}
