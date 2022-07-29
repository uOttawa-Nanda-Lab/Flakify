package io.undertow.server.security;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DigestAuthentication2069TestCase {
/** 
 * Basic test to prove detection of the ResponseHandler response.
 */
@Test public void testNoMechanisms() throws Exception {
  DefaultServer.setRootHandler(new ResponseHandler());
  TestHttpClient client=new TestHttpClient();
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL());
  HttpResponse result=client.execute(get);
  assertEquals(200,result.getStatusLine().getStatusCode());
  Header[] values=result.getHeaders("ProcessedBy");
  assertEquals(1,values.length);
  assertEquals("ResponseHandler",values[0].getValue());
}

}