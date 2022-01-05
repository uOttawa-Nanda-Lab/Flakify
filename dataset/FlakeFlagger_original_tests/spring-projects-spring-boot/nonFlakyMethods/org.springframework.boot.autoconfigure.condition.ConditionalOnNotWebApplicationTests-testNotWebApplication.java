@Test public void testNotWebApplication(){
  this.context.register(MissingConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("foo"));
}
