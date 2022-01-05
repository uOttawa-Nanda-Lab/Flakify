@Test public void registrationOverride() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(CustomDispatcherRegistration.class,ServerPropertiesAutoConfiguration.class,DispatcherServletAutoConfiguration.class);
  this.context.setServletContext(new MockServletContext());
  this.context.refresh();
  ServletRegistrationBean registration=this.context.getBean(ServletRegistrationBean.class);
  assertEquals("[/foo]",registration.getUrlMappings().toString());
  assertEquals("customDispatcher",registration.getServletName());
  assertEquals(0,this.context.getBeanNamesForType(DispatcherServlet.class).length);
}
