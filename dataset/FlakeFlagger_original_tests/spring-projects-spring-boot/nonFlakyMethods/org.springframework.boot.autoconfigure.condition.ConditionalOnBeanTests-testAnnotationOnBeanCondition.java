@Test public void testAnnotationOnBeanCondition(){
  this.context.register(FooConfiguration.class,OnAnnotationConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
