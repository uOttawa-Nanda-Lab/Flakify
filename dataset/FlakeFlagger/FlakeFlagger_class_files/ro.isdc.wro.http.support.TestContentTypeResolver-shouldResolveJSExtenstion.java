package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveJSExtenstion(){assertEquals("application/javascript",ContentTypeResolver.get("/ad/df/mefile.js"));}
}
