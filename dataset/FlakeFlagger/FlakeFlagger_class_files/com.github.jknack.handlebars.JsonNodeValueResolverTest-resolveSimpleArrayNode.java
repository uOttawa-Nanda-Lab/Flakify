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
  public void resolveSimpleArrayNode() throws IOException {
    Handlebars handlebars = new Handlebars();

    Map<String, Object> root = new HashMap<String, Object>();
    root.put("array", new Object[]{1, 2, 3 });

    assertEquals("123",
        handlebars.compile("{{array.[0]}}{{array.[1]}}{{array.[2]}}").apply(context(root)));
    assertEquals("123", handlebars.compile("{{#array}}{{this}}{{/array}}").apply(context(root)));
  }
}
