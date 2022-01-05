@Test public void servletAndFilterBeans() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  Servlet servlet=mock(Servlet.class);
  Filter filter1=mock(Filter.class,withSettings().extraInterfaces(Ordered.class));
  given(((Ordered)filter1).getOrder()).willReturn(1);
  Filter filter2=mock(Filter.class,withSettings().extraInterfaces(Ordered.class));
  given(((Ordered)filter2).getOrder()).willReturn(2);
  this.context.registerBeanDefinition("servletBean",beanDefinition(servlet));
  this.context.registerBeanDefinition("filterBean2",beanDefinition(filter2));
  this.context.registerBeanDefinition("filterBean1",beanDefinition(filter1));
  this.context.refresh();
  MockEmbeddedServletContainerFactory escf=getEmbeddedServletContainerFactory();
  ServletContext servletContext=escf.getServletContext();
  InOrder ordered=inOrder(servletContext);
  verify(escf.getServletContext()).addServlet("servletBean",servlet);
  verify(escf.getRegisteredServlet(0).getRegistration()).addMapping("/");
  ordered.verify(escf.getServletContext()).addFilter("filterBean1",filter1);
  ordered.verify(escf.getServletContext()).addFilter("filterBean2",filter2);
  verify(escf.getRegisteredFilter(0).getRegistration()).addMappingForUrlPatterns(FilterRegistrationBean.ASYNC_DISPATCHER_TYPES,false,"/*");
  verify(escf.getRegisteredFilter(1).getRegistration()).addMappingForUrlPatterns(FilterRegistrationBean.ASYNC_DISPATCHER_TYPES,false,"/*");
}
