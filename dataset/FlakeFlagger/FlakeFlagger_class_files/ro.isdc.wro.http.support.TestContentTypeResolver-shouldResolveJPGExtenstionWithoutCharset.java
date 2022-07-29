package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveJPGExtenstionWithoutCharset(){assertEquals("image/jpeg",ContentTypeResolver.get("s/bvews/omefile.jpg","UTF-8"));}
}
