package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class JsonNodeValueResolverTest {

  @Test
  public void emptyPropertySet() throws IOException {
    Set<Entry<String, Object>> propertySet = JsonNodeValueResolver.INSTANCE
        .propertySet(new Object());
    assertNotNull(propertySet);
    assertEquals(0, propertySet.size());
  }
}
