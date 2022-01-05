@Test public void propertyValueEqualsFalse(){
  load(MultiplePropertiesRequiredConfiguration.class,"property1=false","property2=value2");
  assertFalse(this.context.containsBean("foo"));
}
