@Test public void registrationProperties() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(ServerPropertiesAutoConfiguration.class,DispatcherServletAutoConfiguration.class);
  this.context.setServletContext(new MockServletContext());
  this.context.refresh();
  assertNotNull(this.context.getBean(DispatcherServlet.class));
  ServletRegistrationBean registration=this.context.getBean(ServletRegistrationBean.class);
  assertEquals("[/]",registration.getUrlMappings().toString());
}
