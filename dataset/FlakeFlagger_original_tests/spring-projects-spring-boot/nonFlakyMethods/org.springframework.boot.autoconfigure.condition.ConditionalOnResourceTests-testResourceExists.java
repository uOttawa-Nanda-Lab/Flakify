@Test public void testResourceExists(){
  this.context.register(BasicConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("foo"));
  assertEquals("foo",this.context.getBean("foo"));
}
