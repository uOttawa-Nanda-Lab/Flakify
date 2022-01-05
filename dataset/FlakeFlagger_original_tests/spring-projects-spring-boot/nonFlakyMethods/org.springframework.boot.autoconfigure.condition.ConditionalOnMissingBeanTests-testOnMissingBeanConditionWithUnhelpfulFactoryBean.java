@Test public void testOnMissingBeanConditionWithUnhelpfulFactoryBean(){
  this.context.register(UnhelpfulFactoryBeanConfiguration.class,ConditionalOnFactoryBean.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBeansOfType(ExampleBean.class).values().size(),equalTo(2));
}
