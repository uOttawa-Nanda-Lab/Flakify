@Test public void testAnnotationOnMissingBeanCondition(){
  this.context.register(FooConfiguration.class,OnAnnotationConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
  assertEquals("foo",this.context.getBean("foo"));
}
