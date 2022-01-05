@Test public void testMissingOnClassCondition(){
  this.context.register(MissingConfiguration.class,FooConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
  assertEquals("foo",this.context.getBean("foo"));
}
