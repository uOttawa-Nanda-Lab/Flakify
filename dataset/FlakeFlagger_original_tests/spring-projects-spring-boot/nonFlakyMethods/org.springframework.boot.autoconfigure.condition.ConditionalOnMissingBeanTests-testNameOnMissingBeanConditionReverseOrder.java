@Test public void testNameOnMissingBeanConditionReverseOrder(){
  this.context.register(OnBeanNameConfiguration.class,FooConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("foo",this.context.getBean("foo"));
}
