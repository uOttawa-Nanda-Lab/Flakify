@Test public void servletPath() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(ServerPropertiesAutoConfiguration.class,DispatcherServletAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"server.servlet_path:/spring");
  this.context.refresh();
  assertNotNull(this.context.getBean(DispatcherServlet.class));
  ServletRegistrationBean registration=this.context.getBean(ServletRegistrationBean.class);
  assertEquals("[/spring/*]",registration.getUrlMappings().toString());
  assertNull(registration.getMultipartConfig());
}
