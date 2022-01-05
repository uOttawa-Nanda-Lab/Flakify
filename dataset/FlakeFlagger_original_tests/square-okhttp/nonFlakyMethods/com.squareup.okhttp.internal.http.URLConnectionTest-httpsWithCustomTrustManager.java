@Test public void httpsWithCustomTrustManager() throws Exception {
  RecordingHostnameVerifier hostnameVerifier=new RecordingHostnameVerifier();
  RecordingTrustManager trustManager=new RecordingTrustManager();
  SSLContext sc=SSLContext.getInstance("TLS");
  sc.init(null,new TrustManager[]{trustManager},new java.security.SecureRandom());
  client.setHostnameVerifier(hostnameVerifier);
  client.setSslSocketFactory(sc.getSocketFactory());
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().setBody("ABC"));
  server.enqueue(new MockResponse().setBody("DEF"));
  server.enqueue(new MockResponse().setBody("GHI"));
  server.play();
  URL url=server.getUrl("/");
  assertContent("ABC",client.open(url));
  assertContent("DEF",client.open(url));
  assertContent("GHI",client.open(url));
  assertEquals(Arrays.asList("verify " + hostName),hostnameVerifier.calls);
  assertEquals(Arrays.asList("checkServerTrusted [CN=" + hostName + " 1]"),trustManager.calls);
}
