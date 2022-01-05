/** 
 * Tolerate bad https proxy response when using HttpResponseCache. http://b/6754912 
 */
@Test public void connectViaHttpProxyToHttpsUsingBadProxyAndHttpResponseCache() throws Exception {
  initResponseCache();
  server.useHttps(sslContext.getSocketFactory(),true);
  MockResponse response=new MockResponse().setSocketPolicy(SocketPolicy.UPGRADE_TO_SSL_AT_END).setBody("bogus proxy connect response content");
  for (  InetAddress inetAddress : InetAddress.getAllByName(server.getHostName())) {
    server.enqueue(response);
    server.enqueue(response);
  }
  server.play();
  client.setProxy(server.toProxyAddress());
  URL url=new URL("https://android.com/foo");
  client.setSslSocketFactory(sslContext.getSocketFactory());
  connection=client.open(url);
  try {
    connection.getResponseCode();
    fail();
  }
 catch (  IOException expected) {
  }
  RecordedRequest connect=server.takeRequest();
  assertEquals("Connect line failure on proxy","CONNECT android.com:443 HTTP/1.1",connect.getRequestLine());
  assertContains(connect.getHeaders(),"Host: android.com");
}
