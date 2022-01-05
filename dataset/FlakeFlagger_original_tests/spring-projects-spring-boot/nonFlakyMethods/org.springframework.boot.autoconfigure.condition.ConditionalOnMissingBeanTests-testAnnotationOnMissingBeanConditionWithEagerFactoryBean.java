@Test public void testAnnotationOnMissingBeanConditionWithEagerFactoryBean(){
  this.context.register(FooConfiguration.class,OnAnnotationConfiguration.class,FactoryBeanXmlConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
  assertTrue(this.context.containsBean("example"));
  assertEquals("foo",this.context.getBean("foo"));
}
