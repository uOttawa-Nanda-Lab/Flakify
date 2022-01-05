@Test public void testNameOnBeanCondition(){
  this.context.register(FooConfiguration.class,OnBeanNameConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
