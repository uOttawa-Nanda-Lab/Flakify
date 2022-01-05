@Test public void testOnMissingBeanConditionWithFactoryBean(){
  this.context.register(FactoryBeanConfiguration.class,ConditionalOnFactoryBean.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBean(ExampleBean.class).toString(),equalTo("fromFactory"));
}
