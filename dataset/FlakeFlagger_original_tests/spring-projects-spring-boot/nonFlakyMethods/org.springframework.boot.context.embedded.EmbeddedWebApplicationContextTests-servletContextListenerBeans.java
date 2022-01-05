@Test public void servletContextListenerBeans() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  ServletContextListener initializer=mock(ServletContextListener.class);
  this.context.registerBeanDefinition("initializerBean",beanDefinition(initializer));
  this.context.refresh();
  ServletContext servletContext=getEmbeddedServletContainerFactory().getServletContext();
  verify(servletContext).addListener(initializer);
}
