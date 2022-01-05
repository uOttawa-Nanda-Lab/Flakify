@Test public void testNameOnBeanConditionReverseOrder(){
  this.context.register(OnBeanNameConfiguration.class,FooConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
}
