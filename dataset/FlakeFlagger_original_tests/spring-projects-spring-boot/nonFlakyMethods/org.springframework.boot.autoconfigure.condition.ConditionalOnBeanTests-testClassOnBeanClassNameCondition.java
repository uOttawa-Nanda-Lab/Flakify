@Test public void testClassOnBeanClassNameCondition(){
  this.context.register(FooConfiguration.class,OnBeanClassNameConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
