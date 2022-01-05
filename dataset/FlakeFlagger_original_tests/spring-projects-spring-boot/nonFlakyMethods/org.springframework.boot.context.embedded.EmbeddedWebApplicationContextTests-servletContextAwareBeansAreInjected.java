@Test public void servletContextAwareBeansAreInjected() throws Exception {
  addEmbeddedServletContainerFactoryBean();
  ServletContextAware bean=mock(ServletContextAware.class);
  this.context.registerBeanDefinition("bean",beanDefinition(bean));
  this.context.refresh();
  verify(bean).setServletContext(getEmbeddedServletContainerFactory().getServletContext());
}
