@Test public void sessionScopeAvailableToServlet() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ExampleEmbeddedWebApplicationConfiguration.class,ExampleServletWithAutowired.class,SessionScopedComponent.class);
  Servlet servlet=this.context.getBean(ExampleServletWithAutowired.class);
  assertNotNull(servlet);
}
