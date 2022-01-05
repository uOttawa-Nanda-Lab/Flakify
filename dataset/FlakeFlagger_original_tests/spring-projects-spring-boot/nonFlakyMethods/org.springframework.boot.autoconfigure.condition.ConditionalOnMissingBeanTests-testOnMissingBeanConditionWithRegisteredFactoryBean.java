@Test public void testOnMissingBeanConditionWithRegisteredFactoryBean(){
  this.context.register(RegisteredFactoryBeanConfiguration.class,ConditionalOnFactoryBean.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBean(ExampleBean.class).toString(),equalTo("fromFactory"));
}
