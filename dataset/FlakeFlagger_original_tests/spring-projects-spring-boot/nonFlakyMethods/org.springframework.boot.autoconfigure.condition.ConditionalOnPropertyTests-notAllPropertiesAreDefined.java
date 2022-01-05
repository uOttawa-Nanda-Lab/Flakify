@Test public void notAllPropertiesAreDefined(){
  load(MultiplePropertiesRequiredConfiguration.class,"property1=value1");
  assertFalse(this.context.containsBean("foo"));
}
