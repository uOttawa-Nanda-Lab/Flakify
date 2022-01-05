@Test public void servletContextInitializerBeansSkipsRegisteredServletsAndFilters() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  Servlet servlet=mock(Servlet.class);
  Filter filter=mock(Filter.class);
  ServletRegistrationBean initializer=new ServletRegistrationBean(servlet,"/foo");
  this.context.registerBeanDefinition("initializerBean",beanDefinition(initializer));
  this.context.registerBeanDefinition("servletBean",beanDefinition(servlet));
  this.context.registerBeanDefinition("filterBean",beanDefinition(filter));
  this.context.refresh();
  ServletContext servletContext=getEmbeddedServletContainerFactory().getServletContext();
  verify(servletContext,atMost(1)).addServlet(anyString(),(Servlet)anyObject());
  verify(servletContext,atMost(1)).addFilter(anyString(),(Filter)anyObject());
}
