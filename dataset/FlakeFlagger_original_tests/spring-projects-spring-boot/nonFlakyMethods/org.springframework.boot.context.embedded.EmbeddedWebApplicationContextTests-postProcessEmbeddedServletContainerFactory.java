@Test public void postProcessEmbeddedServletContainerFactory() throws Exception {
  RootBeanDefinition bd=new RootBeanDefinition(MockEmbeddedServletContainerFactory.class);
  MutablePropertyValues pv=new MutablePropertyValues();
  pv.add("port","${port}");
  bd.setPropertyValues(pv);
  this.context.registerBeanDefinition("embeddedServletContainerFactory",bd);
  PropertySourcesPlaceholderConfigurer propertySupport=new PropertySourcesPlaceholderConfigurer();
  Properties properties=new Properties();
  properties.put("port",8080);
  propertySupport.setProperties(properties);
  this.context.registerBeanDefinition("propertySupport",beanDefinition(propertySupport));
  this.context.refresh();
  assertThat(getEmbeddedServletContainerFactory().getContainer().getPort(),equalTo(8080));
}
