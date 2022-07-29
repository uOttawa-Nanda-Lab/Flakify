package ro.isdc.wro.http.support;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestContentTypeResolver {

  @Test public void shouldResolveUnknownExtenstion(){assertEquals("application/octet-stream",ContentTypeResolver.get("/ad/df/mefile.unknown"));}
}
