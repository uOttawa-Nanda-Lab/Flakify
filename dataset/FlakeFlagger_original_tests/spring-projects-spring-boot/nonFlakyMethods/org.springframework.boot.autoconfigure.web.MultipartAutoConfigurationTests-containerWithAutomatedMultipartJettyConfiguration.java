@Test public void containerWithAutomatedMultipartJettyConfiguration(){
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ContainerWithEverythingJetty.class,BaseConfiguration.class);
  this.context.getBean(MultipartConfigElement.class);
  assertSame(this.context.getBean(DispatcherServlet.class).getMultipartResolver(),this.context.getBean(StandardServletMultipartResolver.class));
  verifyServletWorks();
}
