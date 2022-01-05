@Test public void testResourceNotExists(){
  this.context.register(MissingConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("foo"));
}
