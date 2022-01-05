/** 
 * Test which headers are sent unencrypted to the HTTP proxy. 
 */
@Test public void proxyConnectIncludesProxyHeadersOnly() throws IOException, InterruptedException {
  RecordingHostnameVerifier hostnameVerifier=new RecordingHostnameVerifier();
  server.useHttps(sslContext.getSocketFactory(),true);
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.UPGRADE_TO_SSL_AT_END).clearHeaders());
  server.enqueue(new MockResponse().setBody("encrypted response from the origin server"));
  server.play();
  client.setProxy(server.toProxyAddress());
  URL url=new URL("https://android.com/foo");
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(hostnameVerifier);
  connection=client.open(url);
  connection.addRequestProperty("Private","Secret");
  connection.addRequestProperty("Proxy-Authorization","bar");
  connection.addRequestProperty("User-Agent","baz");
  assertContent("encrypted response from the origin server",connection);
  RecordedRequest connect=server.takeRequest();
  assertContainsNoneMatching(connect.getHeaders(),"Private.*");
  assertContains(connect.getHeaders(),"Proxy-Authorization: bar");
  assertContains(connect.getHeaders(),"User-Agent: baz");
  assertContains(connect.getHeaders(),"Host: android.com");
  assertContains(connect.getHeaders(),"Proxy-Connection: Keep-Alive");
  RecordedRequest get=server.takeRequest();
  assertContains(get.getHeaders(),"Private: Secret");
  assertEquals(Arrays.asList("verify android.com"),hostnameVerifier.calls);
}
