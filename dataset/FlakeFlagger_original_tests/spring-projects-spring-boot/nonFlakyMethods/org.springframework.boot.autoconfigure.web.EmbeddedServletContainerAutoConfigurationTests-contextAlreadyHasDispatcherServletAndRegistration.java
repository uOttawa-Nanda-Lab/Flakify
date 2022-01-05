@Test public void contextAlreadyHasDispatcherServletAndRegistration() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(DispatcherServletWithRegistrationConfiguration.class,BaseConfiguration.class);
  verifyContext();
  assertEquals(1,this.context.getBeanNamesForType(DispatcherServlet.class).length);
}
