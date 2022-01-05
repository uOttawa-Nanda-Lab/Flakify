@Test public void containerWithAutomatedMultipartTomcatConfiguration() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ContainerWithEverythingTomcat.class,BaseConfiguration.class);
  new RestTemplate().getForObject("http://localhost:" + this.context.getEmbeddedServletContainer().getPort() + "/",String.class);
  this.context.getBean(MultipartConfigElement.class);
  assertSame(this.context.getBean(DispatcherServlet.class).getMultipartResolver(),this.context.getBean(StandardServletMultipartResolver.class));
  verifyServletWorks();
}
