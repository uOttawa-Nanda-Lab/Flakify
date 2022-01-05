@Test public void testNameOnMissingBeanCondition(){
  this.context.register(FooConfiguration.class,OnBeanNameConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
  assertEquals("foo",this.context.getBean("foo"));
}
