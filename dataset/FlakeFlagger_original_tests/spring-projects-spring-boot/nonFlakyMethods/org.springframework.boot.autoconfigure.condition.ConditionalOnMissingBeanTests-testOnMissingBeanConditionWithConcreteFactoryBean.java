@Test public void testOnMissingBeanConditionWithConcreteFactoryBean(){
  this.context.register(ConcreteFactoryBeanConfiguration.class,ConditionalOnFactoryBean.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBean(ExampleBean.class).toString(),equalTo("fromFactory"));
}
