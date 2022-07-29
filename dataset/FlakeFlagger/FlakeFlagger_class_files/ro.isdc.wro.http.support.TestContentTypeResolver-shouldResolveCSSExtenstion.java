package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveCSSExtenstion(){assertEquals("text/css",ContentTypeResolver.get("somefile.css"));}
}
