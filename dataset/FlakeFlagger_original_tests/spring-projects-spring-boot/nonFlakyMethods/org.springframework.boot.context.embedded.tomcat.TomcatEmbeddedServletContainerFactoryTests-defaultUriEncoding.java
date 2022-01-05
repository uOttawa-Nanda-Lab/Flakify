@Test public void defaultUriEncoding() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  Tomcat tomcat=getTomcat(factory);
  assertEquals("UTF-8",tomcat.getConnector().getURIEncoding());
}
