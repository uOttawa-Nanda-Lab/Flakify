@Test public void testOnMissingBeanType() throws Exception {
  this.context.register(FooConfiguration.class,OnBeanMissingClassConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
}
