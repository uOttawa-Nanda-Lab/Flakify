package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveFontExtensionEot(){assertEquals("application/vnd.ms-fontobject",ContentTypeResolver.get("font.eot"));}
}
