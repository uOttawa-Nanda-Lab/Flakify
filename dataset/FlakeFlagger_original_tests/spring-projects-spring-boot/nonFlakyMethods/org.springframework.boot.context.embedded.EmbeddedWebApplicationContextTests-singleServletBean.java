@Test public void singleServletBean() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  Servlet servlet=mock(Servlet.class);
  this.context.registerBeanDefinition("servletBean",beanDefinition(servlet));
  this.context.refresh();
  MockEmbeddedServletContainerFactory escf=getEmbeddedServletContainerFactory();
  verify(escf.getServletContext()).addServlet("servletBean",servlet);
  verify(escf.getRegisteredServlet(0).getRegistration()).addMapping("/");
}
