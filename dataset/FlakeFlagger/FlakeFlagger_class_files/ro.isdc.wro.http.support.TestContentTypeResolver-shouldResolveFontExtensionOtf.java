package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveFontExtensionOtf(){assertEquals("application/x-font-opentype",ContentTypeResolver.get("font.otf"));}
}
