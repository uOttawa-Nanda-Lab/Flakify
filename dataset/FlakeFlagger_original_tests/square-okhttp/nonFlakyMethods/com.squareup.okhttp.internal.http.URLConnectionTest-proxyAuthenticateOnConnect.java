@Test public void proxyAuthenticateOnConnect() throws Exception {
  Authenticator.setDefault(new RecordingAuthenticator());
  server.useHttps(sslContext.getSocketFactory(),true);
  server.enqueue(new MockResponse().setResponseCode(407).addHeader("Proxy-Authenticate: Basic realm=\"localhost\""));
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.UPGRADE_TO_SSL_AT_END).clearHeaders());
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  client.setProxy(server.toProxyAddress());
  URL url=new URL("https://android.com/foo");
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  connection=client.open(url);
  assertContent("A",connection);
  RecordedRequest connect1=server.takeRequest();
  assertEquals("CONNECT android.com:443 HTTP/1.1",connect1.getRequestLine());
  assertContainsNoneMatching(connect1.getHeaders(),"Proxy\\-Authorization.*");
  RecordedRequest connect2=server.takeRequest();
  assertEquals("CONNECT android.com:443 HTTP/1.1",connect2.getRequestLine());
  assertContains(connect2.getHeaders(),"Proxy-Authorization: Basic " + RecordingAuthenticator.BASE_64_CREDENTIALS);
  RecordedRequest get=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",get.getRequestLine());
  assertContainsNoneMatching(get.getHeaders(),"Proxy\\-Authorization.*");
}
