@Test public void varyAndHttps() throws Exception {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").addHeader("Vary: Accept-Language").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  HttpsURLConnection connection1=(HttpsURLConnection)client.open(url);
  connection1.setSSLSocketFactory(sslContext.getSocketFactory());
  connection1.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  connection1.addRequestProperty("Accept-Language","en-US");
  assertEquals("A",readAscii(connection1));
  HttpsURLConnection connection2=(HttpsURLConnection)client.open(url);
  connection2.setSSLSocketFactory(sslContext.getSocketFactory());
  connection2.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  connection2.addRequestProperty("Accept-Language","en-US");
  assertEquals("A",readAscii(connection2));
}
