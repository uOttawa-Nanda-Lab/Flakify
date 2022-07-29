package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveHTMLExtenstion(){assertEquals("text/html",ContentTypeResolver.get("mefile.html"));}
}
