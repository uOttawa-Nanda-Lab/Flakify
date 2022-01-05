@Test public void pkcs12KeyStoreAndTrustStore() throws Exception {
  FileCopyUtils.copy("test",new FileWriter(this.temporaryFolder.newFile("test.txt")));
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setDocumentRoot(this.temporaryFolder.getRoot());
  Ssl ssl=new Ssl();
  ssl.setKeyStore("src/test/resources/test.p12");
  ssl.setKeyStorePassword("secret");
  ssl.setKeyStoreType("pkcs12");
  ssl.setTrustStore("src/test/resources/test.p12");
  ssl.setTrustStorePassword("secret");
  ssl.setTrustStoreType("pkcs12");
  ssl.setClientAuth(ClientAuth.NEED);
  factory.setSsl(ssl);
  this.container=factory.getEmbeddedServletContainer();
  this.container.start();
  KeyStore keyStore=KeyStore.getInstance("pkcs12");
  keyStore.load(new FileInputStream(new File("src/test/resources/test.p12")),"secret".toCharArray());
  SSLConnectionSocketFactory socketFactory=new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null,new TrustSelfSignedStrategy()).loadKeyMaterial(keyStore,"secret".toCharArray()).build());
  HttpClient httpClient=HttpClients.custom().setSSLSocketFactory(socketFactory).build();
  HttpComponentsClientHttpRequestFactory requestFactory=new HttpComponentsClientHttpRequestFactory(httpClient);
  assertThat(getResponse(getLocalUrl("https","/test.txt"),requestFactory),equalTo("test"));
}
