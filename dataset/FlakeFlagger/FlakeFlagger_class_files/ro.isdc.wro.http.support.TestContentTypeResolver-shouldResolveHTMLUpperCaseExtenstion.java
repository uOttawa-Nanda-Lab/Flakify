package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveHTMLUpperCaseExtenstion(){assertEquals("text/css",ContentTypeResolver.get("mefile.CSS"));}
}
