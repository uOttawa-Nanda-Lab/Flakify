@Test public void usingValueAttribute() throws Exception {
  load(ValueAttribute.class,"some.property");
  assertTrue(this.context.containsBean("foo"));
}
