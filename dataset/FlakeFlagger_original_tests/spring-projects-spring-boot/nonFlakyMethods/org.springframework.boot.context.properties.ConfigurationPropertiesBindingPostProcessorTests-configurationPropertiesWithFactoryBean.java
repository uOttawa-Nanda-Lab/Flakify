@Test public void configurationPropertiesWithFactoryBean() throws Exception {
  ConfigurationPropertiesWithFactoryBean.factoryBeanInit=false;
  this.context=new AnnotationConfigApplicationContext(){
    @Override protected void onRefresh() throws BeansException {
      assertFalse("Init too early",ConfigurationPropertiesWithFactoryBean.factoryBeanInit);
      super.onRefresh();
    }
  }
;
  this.context.register(ConfigurationPropertiesWithFactoryBean.class);
  GenericBeanDefinition beanDefinition=new GenericBeanDefinition();
  beanDefinition.setBeanClass(FactoryBeanTester.class);
  beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
  this.context.registerBeanDefinition("test",beanDefinition);
  this.context.refresh();
  assertTrue("No init",ConfigurationPropertiesWithFactoryBean.factoryBeanInit);
}
