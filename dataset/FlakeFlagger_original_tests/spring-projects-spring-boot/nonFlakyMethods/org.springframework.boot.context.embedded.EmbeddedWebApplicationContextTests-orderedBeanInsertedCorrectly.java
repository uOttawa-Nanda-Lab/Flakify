@Test public void orderedBeanInsertedCorrectly() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  OrderedFilter filter=new OrderedFilter();
  this.context.registerBeanDefinition("filterBean",beanDefinition(filter));
  FilterRegistrationBean registration=new FilterRegistrationBean();
  registration.setFilter(mock(Filter.class));
  registration.setOrder(100);
  this.context.registerBeanDefinition("filterRegistrationBean",beanDefinition(registration));
  this.context.refresh();
  MockEmbeddedServletContainerFactory escf=getEmbeddedServletContainerFactory();
  verify(escf.getServletContext()).addFilter("filterBean",filter);
  verify(escf.getServletContext()).addFilter("object",registration.getFilter());
  assertEquals(filter,escf.getRegisteredFilter(0).getFilter());
}
