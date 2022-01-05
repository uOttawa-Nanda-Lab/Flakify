@Test public void createAndInitializeWithParent() throws Exception {
  AnnotationConfigEmbeddedWebApplicationContext parent=new AnnotationConfigEmbeddedWebApplicationContext(EmbeddedContainerConfiguration.class);
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(EmbeddedContainerConfiguration.class,ServletContextAwareConfiguration.class);
  this.context.setParent(parent);
  this.context.refresh();
  verifyContext();
  assertNotNull(this.context.getBean(ServletContextAwareConfiguration.class).getServletContext());
}
