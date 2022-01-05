@Test public void testNotWebApplication(){
  this.context.register(MissingConfiguration.class);
  this.context.setServletContext(new MockServletContext());
  this.context.refresh();
  assertFalse(this.context.containsBean("foo"));
}
