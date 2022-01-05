@Test public void contextAlreadyHasDispatcherServlet() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(SpringServletConfiguration.class,BaseConfiguration.class);
  verifyContext();
  assertEquals(2,this.context.getBeanNamesForType(DispatcherServlet.class).length);
}
