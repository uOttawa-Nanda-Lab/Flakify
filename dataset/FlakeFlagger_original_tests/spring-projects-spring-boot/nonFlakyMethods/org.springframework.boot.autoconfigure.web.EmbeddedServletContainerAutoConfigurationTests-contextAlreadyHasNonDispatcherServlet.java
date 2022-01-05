@Test public void contextAlreadyHasNonDispatcherServlet() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(NonSpringServletConfiguration.class,BaseConfiguration.class);
  verifyContext();
  assertEquals(0,this.context.getBeanNamesForType(DispatcherServlet.class).length);
}
