@Test public void testClassOnBeanCondition(){
  this.context.register(FooConfiguration.class,OnBeanClassConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
