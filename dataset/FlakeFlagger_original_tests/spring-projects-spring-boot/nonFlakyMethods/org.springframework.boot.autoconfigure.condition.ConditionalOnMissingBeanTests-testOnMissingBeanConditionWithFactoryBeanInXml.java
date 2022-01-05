@Test public void testOnMissingBeanConditionWithFactoryBeanInXml(){
  this.context.register(FactoryBeanXmlConfiguration.class,ConditionalOnFactoryBean.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBean(ExampleBean.class).toString(),equalTo("fromFactory"));
}
