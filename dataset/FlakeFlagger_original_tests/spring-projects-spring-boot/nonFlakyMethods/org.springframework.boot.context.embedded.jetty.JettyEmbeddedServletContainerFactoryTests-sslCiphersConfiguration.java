@Test public void sslCiphersConfiguration() throws Exception {
  Ssl ssl=new Ssl();
  ssl.setKeyStore("src/test/resources/test.jks");
  ssl.setKeyStorePassword("secret");
  ssl.setKeyPassword("password");
  ssl.setCiphers(new String[]{"ALPHA","BRAVO","CHARLIE"});
  JettyEmbeddedServletContainerFactory factory=getFactory();
  factory.setSsl(ssl);
  this.container=factory.getEmbeddedServletContainer();
  this.container.start();
  JettyEmbeddedServletContainer jettyContainer=(JettyEmbeddedServletContainer)this.container;
  SslConnector sslConnector=(SslConnector)jettyContainer.getServer().getConnectors()[0];
  assertThat(sslConnector.getSslContextFactory().getIncludeCipherSuites(),equalTo(new String[]{"ALPHA","BRAVO","CHARLIE"}));
}
