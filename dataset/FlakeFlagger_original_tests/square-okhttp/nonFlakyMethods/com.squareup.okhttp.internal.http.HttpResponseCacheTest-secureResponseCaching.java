@Test public void secureResponseCaching() throws IOException {
  server.useHttps(sslContext.getSocketFactory(),false);
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-1,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(1,TimeUnit.HOURS)).setBody("ABC"));
  server.play();
  HttpsURLConnection c1=(HttpsURLConnection)client.open(server.getUrl("/"));
  c1.setSSLSocketFactory(sslContext.getSocketFactory());
  c1.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  assertEquals("ABC",readAscii(c1));
  String suite=c1.getCipherSuite();
  List<Certificate> localCerts=toListOrNull(c1.getLocalCertificates());
  List<Certificate> serverCerts=toListOrNull(c1.getServerCertificates());
  Principal peerPrincipal=c1.getPeerPrincipal();
  Principal localPrincipal=c1.getLocalPrincipal();
  HttpsURLConnection c2=(HttpsURLConnection)client.open(server.getUrl("/"));
  c2.setSSLSocketFactory(sslContext.getSocketFactory());
  c2.setHostnameVerifier(NULL_HOSTNAME_VERIFIER);
  assertEquals("ABC",readAscii(c2));
  assertEquals(2,cache.getRequestCount());
  assertEquals(1,cache.getNetworkCount());
  assertEquals(1,cache.getHitCount());
  assertEquals(suite,c2.getCipherSuite());
  assertEquals(localCerts,toListOrNull(c2.getLocalCertificates()));
  assertEquals(serverCerts,toListOrNull(c2.getServerCertificates()));
  assertEquals(peerPrincipal,c2.getPeerPrincipal());
  assertEquals(localPrincipal,c2.getLocalPrincipal());
}
