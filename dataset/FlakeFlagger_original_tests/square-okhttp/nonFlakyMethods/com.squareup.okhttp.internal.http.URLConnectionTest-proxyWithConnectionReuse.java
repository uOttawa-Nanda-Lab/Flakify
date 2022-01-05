@Test public void proxyWithConnectionReuse() throws IOException {
  SSLSocketFactory socketFactory=sslContext.getSocketFactory();
  RecordingHostnameVerifier hostnameVerifier=new RecordingHostnameVerifier();
  server.useHttps(socketFactory,true);
  server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.UPGRADE_TO_SSL_AT_END).clearHeaders());
  server.enqueue(new MockResponse().setBody("response 1"));
  server.enqueue(new MockResponse().setBody("response 2"));
  server.play();
  client.setProxy(server.toProxyAddress());
  URL url=new URL("https://android.com/foo");
  client.setSslSocketFactory(socketFactory);
  client.setHostnameVerifier(hostnameVerifier);
  assertContent("response 1",client.open(url));
  assertContent("response 2",client.open(url));
}
