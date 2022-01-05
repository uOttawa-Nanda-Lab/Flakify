@Test public void sslWantsClientAuthenticationSucceedsWithoutClientCertificate() throws Exception {
  FileCopyUtils.copy("test",new FileWriter(this.temporaryFolder.newFile("test.txt")));
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setDocumentRoot(this.temporaryFolder.getRoot());
  Ssl ssl=new Ssl();
  ssl.setKeyStore("src/test/resources/test.jks");
  ssl.setKeyStorePassword("secret");
  ssl.setKeyPassword("password");
  ssl.setClientAuth(ClientAuth.WANT);
  ssl.setTrustStore("src/test/resources/test.jks");
  ssl.setTrustStorePassword("secret");
  factory.setSsl(ssl);
  this.container=factory.getEmbeddedServletContainer();
  this.container.start();
  SSLConnectionSocketFactory socketFactory=new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null,new TrustSelfSignedStrategy()).build());
  HttpClient httpClient=HttpClients.custom().setSSLSocketFactory(socketFactory).build();
  HttpComponentsClientHttpRequestFactory requestFactory=new HttpComponentsClientHttpRequestFactory(httpClient);
  assertThat(getResponse(getLocalUrl("https","/test.txt"),requestFactory),equalTo("test"));
}
