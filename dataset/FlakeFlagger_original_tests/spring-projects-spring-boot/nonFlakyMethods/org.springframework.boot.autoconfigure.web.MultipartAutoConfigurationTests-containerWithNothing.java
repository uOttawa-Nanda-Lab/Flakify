@Test public void containerWithNothing(){
  this.context=new AnnotationConfigEmbeddedWebApplicationContext(ContainerWithNothing.class,BaseConfiguration.class);
  DispatcherServlet servlet=this.context.getBean(DispatcherServlet.class);
  assertNotNull(servlet.getMultipartResolver());
  assertThat(this.context.getBeansOfType(StandardServletMultipartResolver.class).size(),equalTo(1));
  assertThat(this.context.getBeansOfType(MultipartResolver.class).size(),equalTo(1));
}
