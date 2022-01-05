@Test public void uriEncoding() throws Exception {
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  factory.setUriEncoding("US-ASCII");
  Tomcat tomcat=getTomcat(factory);
  assertEquals("US-ASCII",tomcat.getConnector().getURIEncoding());
}
