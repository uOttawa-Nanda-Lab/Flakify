@Test public void contextAlreadyHasNonServlet() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(NonServletConfiguration.class,BaseConfiguration.class);
  assertEquals(0,this.context.getBeanNamesForType(DispatcherServlet.class).length);
  assertEquals(0,this.context.getBeanNamesForType(Servlet.class).length);
}
