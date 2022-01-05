@Test public void testVanillaOnClassCondition(){
  this.context.register(BasicConfiguration.class,FooConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
