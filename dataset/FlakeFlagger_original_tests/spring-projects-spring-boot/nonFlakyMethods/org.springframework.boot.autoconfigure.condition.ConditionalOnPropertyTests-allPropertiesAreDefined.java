@Test public void allPropertiesAreDefined(){
  load(MultiplePropertiesRequiredConfiguration.class,"property1=value1","property2=value2");
  assertTrue(this.context.containsBean("foo"));
}
