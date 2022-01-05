@Test public void createAndInitializeCyclic() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ServletContextAwareEmbeddedConfiguration.class);
  verifyContext();
  assertNull(this.context.getBean(ServletContextAwareEmbeddedConfiguration.class).getServletContext());
}
