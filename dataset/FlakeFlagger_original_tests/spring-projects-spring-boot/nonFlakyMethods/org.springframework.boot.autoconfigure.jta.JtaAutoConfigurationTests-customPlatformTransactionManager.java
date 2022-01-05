@Test public void customPlatformTransactionManager() throws Exception {
  this.context=new AnnotationConfigApplicationContext(CustomTransactionManagerConfig.class,JtaAutoConfiguration.class);
  this.thrown.expect(NoSuchBeanDefinitionException.class);
  this.context.getBean(JtaTransactionManager.class);
}
