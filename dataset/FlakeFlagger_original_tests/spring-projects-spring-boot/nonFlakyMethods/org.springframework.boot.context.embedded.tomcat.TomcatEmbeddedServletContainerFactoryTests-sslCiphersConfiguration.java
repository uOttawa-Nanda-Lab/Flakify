@Test public void sslCiphersConfiguration() throws Exception {
  Ssl ssl=new Ssl();
  ssl.setKeyStore("test.jks");
  ssl.setKeyStorePassword("secret");
  ssl.setCiphers(new String[]{"ALPHA","BRAVO","CHARLIE"});
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  factory.setSsl(ssl);
  Tomcat tomcat=getTomcat(factory);
  Connector connector=tomcat.getConnector();
  AbstractHttp11JsseProtocol<?> jsseProtocol=(AbstractHttp11JsseProtocol<?>)connector.getProtocolHandler();
  assertThat(jsseProtocol.getCiphers(),equalTo("ALPHA,BRAVO,CHARLIE"));
}
