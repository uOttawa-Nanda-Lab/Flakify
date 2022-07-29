package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveHTMLExtenstionWitCharset(){assertEquals("text/html; charset=UTF-8",ContentTypeResolver.get("mefile.html","UTF-8"));}
}
