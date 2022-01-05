@Test public void containerWithNoMultipartTomcatConfiguration(){
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ContainerWithNoMultipartTomcat.class,BaseConfiguration.class);
  DispatcherServlet servlet=this.context.getBean(DispatcherServlet.class);
  assertNull(servlet.getMultipartResolver());
  assertThat(this.context.getBeansOfType(StandardServletMultipartResolver.class).size(),equalTo(1));
  assertThat(this.context.getBeansOfType(MultipartResolver.class).size(),equalTo(1));
  verifyServletWorks();
}
