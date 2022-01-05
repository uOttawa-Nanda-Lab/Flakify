@Test public void testVanillaOnClassCondition(){
  this.context.register(BasicConfiguration.class,FooConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
  assertEquals("foo",this.context.getBean("foo"));
}
