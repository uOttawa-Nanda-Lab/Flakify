@Test public void filterReegistrationBeansSkipsRegisteredFilters() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  Filter filter=mock(Filter.class);
  FilterRegistrationBean initializer=new FilterRegistrationBean(filter);
  this.context.registerBeanDefinition("initializerBean",beanDefinition(initializer));
  this.context.registerBeanDefinition("filterBean",beanDefinition(filter));
  this.context.refresh();
  ServletContext servletContext=getEmbeddedServletContainerFactory().getServletContext();
  verify(servletContext,atMost(1)).addFilter(anyString(),(Filter)anyObject());
}
