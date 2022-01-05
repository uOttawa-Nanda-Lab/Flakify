@Test public void nonRelaxedName() throws Exception {
  load(NonRelaxedPropertiesRequiredConfiguration.class,"theRelaxedProperty=value1");
  assertFalse(this.context.containsBean("foo"));
}
