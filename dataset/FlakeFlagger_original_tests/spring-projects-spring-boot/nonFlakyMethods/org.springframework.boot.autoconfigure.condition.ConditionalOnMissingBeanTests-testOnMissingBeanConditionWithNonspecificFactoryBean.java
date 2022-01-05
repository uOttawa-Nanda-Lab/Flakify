@Test public void testOnMissingBeanConditionWithNonspecificFactoryBean(){
  this.context.register(NonspecificFactoryBeanConfiguration.class,ConditionalOnFactoryBean.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBean(ExampleBean.class).toString(),equalTo("fromFactory"));
}
