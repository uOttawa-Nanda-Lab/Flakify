@Test public void servletContextInitializerBeansDoesNotSkipServletsAndFilters() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  ServletContextInitializer initializer=mock(ServletContextInitializer.class);
  Servlet servlet=mock(Servlet.class);
  Filter filter=mock(Filter.class);
  this.context.registerBeanDefinition("initializerBean",beanDefinition(initializer));
  this.context.registerBeanDefinition("servletBean",beanDefinition(servlet));
  this.context.registerBeanDefinition("filterBean",beanDefinition(filter));
  this.context.refresh();
  ServletContext servletContext=getEmbeddedServletContainerFactory().getServletContext();
  verify(initializer).onStartup(servletContext);
  verify(servletContext).addServlet(anyString(),(Servlet)anyObject());
  verify(servletContext).addFilter(anyString(),(Filter)anyObject());
}
