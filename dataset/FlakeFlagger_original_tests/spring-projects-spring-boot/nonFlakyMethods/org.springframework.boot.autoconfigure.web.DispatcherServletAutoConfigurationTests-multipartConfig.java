@Test public void multipartConfig() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.setServletContext(new MockServletContext());
  this.context.register(MultipartConfiguration.class,ServerPropertiesAutoConfiguration.class,DispatcherServletAutoConfiguration.class);
  this.context.refresh();
  ServletRegistrationBean registration=this.context.getBean(ServletRegistrationBean.class);
  assertNotNull(registration.getMultipartConfig());
}
