@Test public void proxyWithConnectionClose() throws IOException {
  server.useHttps(sslContext.getSocketFactory(),true);
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.UPGRADE_TO_SSL_AT_END).clearHeaders());
  server.enqueue(new MockResponse().setBody("this response comes via a proxy"));
  server.play();
  client.setProxy(server.toProxyAddress());
  URL url=new URL("https://android.com/foo");
  client.setSslSocketFactory(sslContext.getSocketFactory());
  client.setHostnameVerifier(new RecordingHostnameVerifier());
  connection=client.open(url);
  connection.setRequestProperty("Connection","close");
  assertContent("this response comes via a proxy",connection);
}
